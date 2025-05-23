package io.example.jdk14;

public class SwitchDemo {
    public static void main(String[] args){
        // Java 8 风格
        int day = 3;
        String dayName;
        switch (day) {
            case 1: case 2: case 3: case 4: case 5:
                dayName = "Weekday";
                break;
            case 6: case 7:
                dayName = "Weekend";
                break;
            default:
                dayName = "Invalid day";
                break;
        }
        System.out.println(dayName);
        // Java 14+ Switch Expression
        String dayType = switch (day) {
            case 1, 2, 3, 4, 5 -> "Weekday"; // 多个case合并，箭头语法
            case 6, 7 -> "Weekend";
            default -> "Invalid day";
        };
        System.out.println(dayType);
        // 使用 yield
        int numLetters = switch (day) {
            case 1, 2, 6 -> {
                System.out.println("Short day name");
                yield 6; // yield 返回值
            }
            case 3, 4, 5, 7 -> {
                System.out.println("Long day name");
                yield 8;
            }
            default -> {
                System.out.println("Unknown");
                yield 0;
            }
        };
        System.out.println("Number of letters: " + numLetters);
    }
}
