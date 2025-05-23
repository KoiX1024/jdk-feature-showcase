package io.example.jdk9;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiDemo {
    public static void main(String[] args){
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 2, 1);
        List<Integer> lessThanFour = numbers.stream()
                .takeWhile(n -> n < 4)
                .collect(Collectors.toList());
        System.out.println(lessThanFour);
        List<Integer> fromFour = numbers.stream()
                .dropWhile(n -> n < 4)
                .collect(Collectors.toList());
        System.out.println(fromFour);
        String name = null;
        Stream<String> nameStream = Stream.ofNullable(name);
        nameStream.forEach(System.out::println);
        String anotherName = "Java";
        Stream<String> anotherNameStream = Stream.ofNullable(anotherName);
        anotherNameStream.forEach(System.out::println);
    }
}
