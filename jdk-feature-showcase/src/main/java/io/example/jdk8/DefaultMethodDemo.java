package io.example.jdk8;

public class DefaultMethodDemo {
    interface Vehicle {
        void start();
        void stop();

        // 默认方法
        default void honk() {
            System.out.println("Beep beep!");
        }
    }
    static class Car implements Vehicle {
        @Override
        public void start() {
            System.out.println("Car started.");
        }

        @Override
        public void stop() {
            System.out.println("Car stopped.");
        }
        // Car类自动继承了honk()方法
    }
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.start();
        myCar.honk(); // 调用默认方法
        myCar.stop();
    }

}
