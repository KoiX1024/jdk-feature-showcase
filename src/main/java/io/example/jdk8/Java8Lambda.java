package io.example.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Java8Lambda {
    public static void main(String[] args){
        /**
         * 通过实现Comparator接口
         */
        List<String> names1 = Arrays.asList("peter", "anna", "mike", "xenia");

        names1.sort( new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });

        System.out.println(names1);
        /**
         * 使用 Java 8 Lambda
         */
        List<String> names2 = Arrays.asList("peter", "anna", "mike", "xenia");

        names2.sort((String a, String b) -> {
            return a.length() - b.length();
        });
        System.out.println(names2);
        /**
         * 进一步简化
         */
        List<String> names3 = Arrays.asList("peter", "anna", "mike", "xenia");

        names3.sort((a, b) -> a.length() - b.length());
        System.out.println(names3);
        /**
         * 进一步使用函数接口
         */
        List<String> names4 = Arrays.asList("peter", "anna", "mike", "xenia");
        names4.sort(Comparator.comparingInt(String::length));
        System.out.println(names4);

    }
}
