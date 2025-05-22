package io.example.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeApiDemo {
    public static void main(String[] args) {
        // 获取当前日期和时间
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today); // e.g., 2023-10-26

        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now); // e.g., 15:30:45.123

        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentDateTime);

        // 创建特定日期
        LocalDate birthday = LocalDate.of(1990, Month.JANUARY, 15);
        System.out.println("Birthday: " + birthday);

        // 日期计算
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Next week: " + nextWeek);

        // 格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("Formatted: " + formattedDateTime);

        // 解析
        LocalDateTime parsedDateTime = LocalDateTime.parse("2023/11/01 10:00:00", formatter);
        System.out.println("Parsed: " + parsedDateTime);
    }
}
