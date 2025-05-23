package io.example.jdk16;

public record Point(int x, int y) {
    // 编译器会自动生成：
    // 1. All-args constructor (x, y)
    // 2. Getter-like accessors: x(), y()
    // 3. equals()
    // 4. hashCode()
    // 5. toString()
    // Records 是 final 的，其字段也是 final 的
}
