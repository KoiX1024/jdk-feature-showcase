package io.example.jdk21;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

public class StructuredConcurrencyDemo {

    String fetchUserData() throws InterruptedException {
        System.out.println(Thread.currentThread() + " Fetching user data...");
        Thread.sleep(Duration.ofSeconds(1)); // 模拟耗时操作
        // throw new RuntimeException("User service failed!"); // 测试失败场景
        return "User Data";
    }

    String fetchOrderData() throws InterruptedException {
        System.out.println(Thread.currentThread() + " Fetching order data...");
        Thread.sleep(Duration.ofSeconds(2)); // 模拟耗时操作
        return "Order Data";
    }

    // 使用 ShutdownOnFailure 策略
    public String getUserAndOrderData() throws InterruptedException, ExecutionException {
        // 使用 try-with-resources 确保 scope 被关闭
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            // 启动第一个子任务
            Supplier<String> userTask = scope.fork(this::fetchUserData);
            // 启动第二个子任务
            Supplier<String> orderTask = scope.fork(this::fetchOrderData);

            // 等待所有子任务完成或发生故障
            // 如果有任何子任务失败，join() 将抛出异常，其他未完成任务将被取消
            scope.join();
            scope.throwIfFailed(); // 如果任何子任务失败，则抛出该异常

            // 所有子任务都成功完成，获取结果
            String userData = userTask.get(); // .get() 不会阻塞，因为 join() 保证了已完成
            String orderData = orderTask.get();

            return "Combined: " + userData + " & " + orderData;
        }
    }

    // 使用 ShutdownOnSuccess 策略 (例如，从多个镜像源下载，取最快的那个)
    public String getFastestResult() throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            Supplier<String> task1 = scope.fork(() -> {
                Thread.sleep(Duration.ofSeconds(3));
                System.out.println(Thread.currentThread() + " Task 1 finished");
                return "Result from Task 1";
            });
            Supplier<String> task2 = scope.fork(() -> {
                Thread.sleep(Duration.ofSeconds(1));
                System.out.println(Thread.currentThread() + " Task 2 finished");
                return "Result from Task 2";
            });

            // 等待第一个成功的任务完成，并取消其他任务
            scope.join();

            // 获取第一个成功的结果
            // 如果所有任务都失败，result() 会抛出异常
            return scope.result();
        }
    }


    public static void main(String[] args) {
        StructuredConcurrencyDemo demo = new StructuredConcurrencyDemo();

        System.out.println("--- ShutdownOnFailure Demo ---");
        try {
            String combinedData = demo.getUserAndOrderData();
            System.out.println("Final Result: " + combinedData);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Operation failed: " + e.getMessage());
            // e.printStackTrace(); // 打印堆栈跟踪以查看根本原因
        }

        System.out.println("\n--- ShutdownOnSuccess Demo ---");
        try {
            String fastestData = demo.getFastestResult();
            System.out.println("Fastest Result: " + fastestData);
        } catch (InterruptedException | ExecutionException e) { // ExecutionException if all fail
            System.err.println("Operation failed to get fastest: " + e.getMessage());
        }
    }
}
