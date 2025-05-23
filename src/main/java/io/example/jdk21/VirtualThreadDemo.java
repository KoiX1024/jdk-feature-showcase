package io.example.jdk21;

public class VirtualThreadDemo {
    public static void main(String[] args){
        try {
            // 创建并启动⼀个虚拟线程
            Thread virtualThread = Thread.startVirtualThread(() -> {
                System.out.println("Hello from Virtual Thread: " +
                        Thread.currentThread());
                // 模拟耗时I/O操作
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Virtual Thread finished.");
            });
            virtualThread.join(); // 等待虚拟线程执⾏完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
