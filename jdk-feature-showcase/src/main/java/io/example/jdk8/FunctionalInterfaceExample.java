package io.example.jdk8;

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("World");
    }
}
