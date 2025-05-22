package io.example.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        /**
         * java8以前
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evenSquares = new ArrayList<>();
        for (Integer n : numbers) {
            if (n % 2 == 0) {
                evenSquares.add(n * n);
            }
        }
        System.out.println(evenSquares);
        /**
         * 使用java8 stream
         */
        List<Integer> evenSquares1 = numbers.stream() // 1. 获取流
                .filter(n -> n % 2 == 0) // 2. 筛选偶数
                .map(n -> n * n)         // 3. 平方
                .collect(Collectors.toList()); // 4. 收集到List

        System.out.println(evenSquares1);
    }
}
