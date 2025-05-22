package io.example.jdk8;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface GreetingService {
    void sayMessage(String message);
}
