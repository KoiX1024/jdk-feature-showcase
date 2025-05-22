package io.example.jdk8;

import java.util.Arrays;
import java.util.List;

public class MethodReferencesExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(System.out::println);
        // 方法引用 (特定类型的任意对象的实例方法引用)
        List<Integer> nameLengthsRef = names.stream()
                .map(String::length)
                .toList();
        System.out.println(nameLengthsRef);

    }
}
