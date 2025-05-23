package io.example.jdk9;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableCollectionDemo {
    public static void main(String[] args){
        List<String> newList = List.of("Apple", "Banana", "Cherry");
        Set<String> newSet = Set.of("A", "B", "C");
        Map<String, Integer> newMap = Map.of("One", 1, "Two", 2, "Three", 3);
        System.out.println(newList);
        System.out.println(newSet);
        System.out.println(newMap);
    }
}
