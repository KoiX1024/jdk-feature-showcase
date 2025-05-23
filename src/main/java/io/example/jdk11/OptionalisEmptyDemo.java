package io.example.jdk11;

import java.util.Optional;

public class OptionalisEmptyDemo {
    public static void main(String[] args){
        Optional<String> opt = Optional.empty();
        if (opt.isEmpty()) { // Java 11+
            System.out.println("Optional is empty.");
        }
    }
}
