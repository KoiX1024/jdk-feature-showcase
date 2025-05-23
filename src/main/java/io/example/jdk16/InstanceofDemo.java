package io.example.jdk16;

public class InstanceofDemo {
    public static void main(String[] args){
        Object obj = "Hello Java 16";

        // Java 8 风格
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.println("Length: " + s.length());
        }

        // Java 16+ Pattern Matching for instanceof (标准，Java 14/15为预览)
        if (obj instanceof String s) { // 如果obj是String，则自动转换为s
            System.out.println("Length: " + s.length());
        } else if (obj instanceof Integer i) {
            System.out.println("Value: " + i);
        }
    }
}
