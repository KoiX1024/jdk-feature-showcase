package io.example.jdk17;

public class SwitchPatternMatchingDemo {
    static String formatter(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            case null      -> "null object"; // 处理 null
            default        -> o.toString();
        };
    }

    public static void main(String[] args){
        System.out.println(formatter(123));      // int 123
        System.out.println(formatter("Hello"));   // String Hello
        System.out.println(formatter(null));      // null object
        System.out.println(formatter(10.5));    // double 10.500000
    }
}
