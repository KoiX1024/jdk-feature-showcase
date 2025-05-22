package io.example.jdk8;

import java.util.Optional;

public class OptionalDemo {
    public static Optional<String> getNameById(int id) {
        if (id == 1) {
            return Optional.of("Alice");
        } else {
            return Optional.empty(); // 或者 Optional.ofNullable(null)
        }
    }

    public static void main(String[] args) {
        Optional<String> nameOpt = getNameById(1);

        // 安全地获取值
        String name = nameOpt.orElse("Unknown");
        System.out.println(name); // Alice

        Optional<String> nameOpt2 = getNameById(2);
        String name2 = nameOpt2.orElseGet(() -> "Default User " + System.currentTimeMillis());
        System.out.println(name2); // Default User [timestamp]

        // 使用map转换
        nameOpt.map(String::toUpperCase)
                .ifPresent(System.out::println); // ALICE
    }
}
