//package java_1.lesson_1;

/**
 * Created by асер on 05.10.2016.
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
    }

    public static int calculate(int a, int b, int c, int d){
        return a * (b + (c / d));
    }

    public static boolean checkRange(int a, int b){
        if ((a + b) < 10 || (a + b) > 20){
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkYear(int year){
        boolean result = false;
        if (year % 4 == 0){
            result = true;
            if (year % 100 == 0){
                result = false;
            }
            if (year % 400 == 0){
                result = true;
            }
        }
        return result;
    }
}
