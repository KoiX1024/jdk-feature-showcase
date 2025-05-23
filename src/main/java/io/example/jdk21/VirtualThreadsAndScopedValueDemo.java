package io.example.jdk21;

import java.util.NoSuchElementException;
import java.util.concurrent.*;
import java.util.Random;

public class VirtualThreadsAndScopedValueDemo {

    // 1. 定义 ScopedValue 实例
    // ScopedValue 应该是 static final 的
    // 它本身不存储值，而是作为一个键来查找在当前作用域绑定的值
    private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();
    private static final ScopedValue<String> USER_ID = ScopedValue.newInstance();

    private static final Random random = new Random();

    public static void main(String[] args) {
        // 使用 try-with-resources 来确保 ExecutorService 被正确关闭
        // Executors.newVirtualThreadPerTaskExecutor() 会为每个任务创建一个新的虚拟线程
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // 模拟处理三个不同的请求
            executor.submit(() -> handleRequest("user-alice", "req-001"));
            executor.submit(() -> handleRequest("user-bob", "req-002"));
            executor.submit(() -> handleRequest("user-charlie", "req-003"));

            // 演示如果 ScopedValue 未绑定时的情况
            executor.submit(() -> {
                log("Trying to access values outside of a bound scope:");
                // 如果值未绑定，get() 会抛出 NoSuchElementException
                // orElse() 提供了一个默认值
                log("REQUEST_ID (orElse): " + REQUEST_ID.orElse("NOT_BOUND"));
                log("USER_ID (orElse): " + USER_ID.orElse("NOT_BOUND"));
                try {
                    REQUEST_ID.get(); // 这会抛出异常
                } catch (NoSuchElementException e) {
                    log("REQUEST_ID.get() threw: " + e.getMessage());
                }
            });

            // 等待所有任务完成 (或者超时)
            executor.shutdown();
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                log("Executor did not terminate in time.");
                executor.shutdownNow();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("Main thread interrupted: " + e.getMessage());
        }
        log("All requests processed. Main thread exiting.");
    }

    // 模拟处理一个请求的入口点
    private static void handleRequest(String currentUserId, String currentRequestId) {
        // 2. 绑定 ScopedValue 的值
        // ScopedValue.where(key, value) 创建一个绑定。
        // .run(Runnable) 或 .call(Callable) 执行指定的操作，在该操作期间值是可用的。
        // 可以链式调用 .where() 来绑定多个 ScopedValue
        ScopedValue.where(REQUEST_ID, currentRequestId)
                .where(USER_ID, currentUserId)
                .run(() -> {
                    log("Request handling started.");

                    performBusinessLogic();
                    callExternalService();
                    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
                        scope.fork(() -> {
                            asyncSubTask(); // ScopedValue 会被正确传播
                            return null;
                        });
                        scope.join();
                        scope.throwIfFailed();
                    } catch (InterruptedException | ExecutionException e) {
                        Thread.currentThread().interrupt();
                    }
                    log("Request handling finished.");
                });
        // 在 .run() 方法结束后，REQUEST_ID 和 USER_ID 就不再绑定在这个线程的这个执行点了
    }

    private static void performBusinessLogic() {
        // 3. 在作用域内访问 ScopedValue 的值
        // 不需要传递参数，直接通过 ScopedValue 实例的 get() 方法获取
        String reqId = REQUEST_ID.get(); // 如果未绑定，会抛出 NoSuchElementException
        String usrId = USER_ID.get();

        log("Performing business logic for User: " + usrId + ", Request: " + reqId);
        try {
            Thread.sleep(random.nextInt(50) + 50); // 模拟工作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("Business logic interrupted.");
        }
    }

    private static void callExternalService() {
        // 即使在更深层次的调用栈中，只要在 ScopedValue.where().run() 的动态范围内，
        // 绑定的值依然可用。
        String reqId = REQUEST_ID.get();
        String usrId = USER_ID.get();

        log("Calling external service for User: " + usrId + ", Request: " + reqId);
        try {
            Thread.sleep(random.nextInt(100) + 100); // 模拟网络延迟
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("External service call interrupted.");
        }
    }

    private static void asyncSubTask() {
        // 这个方法在由 handleRequest 内部启动的新虚拟线程中运行
        // ScopedValue 的值会被这个新的虚拟线程继承
        if (REQUEST_ID.isBound() && USER_ID.isBound()) {
            String reqId = REQUEST_ID.get();
            String usrId = USER_ID.get();
            log("Async sub-task running for User: " + usrId + ", Request: " + reqId);
        } else {
            log("Async sub-task running, but ScopedValues are not bound (this shouldn't happen in this demo).");
        }
        try {
            Thread.sleep(random.nextInt(30) + 30); // 模拟工作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("Async sub-task interrupted.");
        }
    }

    // 辅助日志方法，打印线程名和消息
    private static void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}
