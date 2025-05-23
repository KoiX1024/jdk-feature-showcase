package io.example.jdk9;

public interface MyInterface {
    // 公开的默认方法
    default void doSomething() {
        init(); // 调用私有方法
        System.out.println("Doing something...");
    }

    // 公开的静态方法
    static void doStaticThing() {
        staticInit(); // 调用私有静态方法
        System.out.println("Doing static thing...");
    }

    // 私有方法 (Java 9+)
    private void init() {
        System.out.println("Initializing from private method...");
    }

    // 私有静态方法 (Java 9+)
    private static void staticInit() {
        System.out.println("Initializing from private static method...");
    }
}
