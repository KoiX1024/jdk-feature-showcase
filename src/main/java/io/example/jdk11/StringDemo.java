package io.example.jdk11;

public class StringDemo {
    public static void main(String[] args){
        String str = "  Hello Java 11  \n  ";
        System.out.println("isBlank: " + "   ".isBlank());
        System.out.println("isBlank: " + str.isBlank());
        str.lines().forEach(System.out::println);
        System.out.println("strip: '" + str.strip() + "'");
        System.out.println("stripLeading: '" + str.stripLeading() + "'");
        System.out.println("stripTrailing: '" + str.stripTrailing() + "'");

        System.out.println("repeat: " + "Java".repeat(3));
    }
}
