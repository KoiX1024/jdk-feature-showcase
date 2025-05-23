package io.example.jdk10;

import java.util.ArrayList;
import java.util.HashMap;

public class VarDemo {
    public static void main(String[] args){
        var varMessage = "Hello, Java 10!";
        System.out.println(varMessage.getClass());
        var varNames = new ArrayList<String>();
        varNames.add("hello");
        System.out.println(varNames.getClass());
        var varMap = new HashMap<String, Integer>();
        varMap.put("key",10);
        System.out.println(varMap.getClass());
        for (var name : varNames) {
            System.out.println(name);
        }
    }
}
