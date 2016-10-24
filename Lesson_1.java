//package java_1.lesson_1;

/**
 * Created by Валентин Фалин on 05.10.2016.
 * Урок 1. Введение в платформу Java.
 * Задание:
 * 1.Создать пустой проект в IntelliJ IDEA и прописать метод main();
 * 2.Создать переменные всех пройденных типов данных, и инициализировать их значения;
 * 3.Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат, где a,b,c,d – входные параметры этого метода.
 * 4.Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах 10 до 20, если да – вернуть true, в противном случае – false.
 * 5.Написать метод, который определяет является ли год високосным. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
 */
public class Lesson_1 {
    public static void main(String[] args) {
        byte a_byte = 127;
        short b_short = 3276;
        int c_int = 213188;
        long d_long = 321321321;
        double e_double = 3.14;
        float f_float = 3.14f;
        boolean g_boolean = false;
        char h_char = 'c';
        System.out.println(calculate(1, 2, 3, 4));
        System.out.println(checkRange(3, 6));
        System.out.println(checkYear(2104));
    }

    static int calculate(int a, int b, int c, int d) {
        return d != 0 ? a * (b + (c / d)) : 0;
    }

    static boolean checkRange(int a, int b) {
        if ((a + b) < 10 || (a + b) > 20) return false;
        return true;
    }

    static boolean checkYear(int year) {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) return true;
        return false;
    }
}
