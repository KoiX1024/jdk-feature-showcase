package io.example.jdk16;

public class RecordDemo {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        System.out.println(p1.x()); // 10 (注意是 x() 不是 getX())
        System.out.println(p1.y()); // 20
        System.out.println(p1);   // Point[x=10, y=20]

        Point p2 = new Point(10, 20);
        System.out.println(p1.equals(p2)); // true
    }
}
