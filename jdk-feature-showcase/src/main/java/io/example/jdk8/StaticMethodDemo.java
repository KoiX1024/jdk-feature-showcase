package io.example.jdk8;

public class StaticMethodDemo {
    interface Utility {
        static boolean isNullOrEmpty(String str) {
            return str == null || str.trim().isEmpty();
        }
    }
    public static void main(String[] args) {
        System.out.println(Utility.isNullOrEmpty(""));    // true
        System.out.println(Utility.isNullOrEmpty("test")); // false
    }
}
