package java_1.lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Валентин Фалин. Урок 2 on 10.10.2016.
 * Калькулятор работает только если писать лексемы через пробел.
 */
class Lesson_2 {
    static int[] arr_1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
    static int[] arr_2 = new int[8];
    static int[] mas = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

    public static void main(String[] args) throws IOException {
        printArr(arr_1);
        reversArr(arr_1);
        printArr(arr_1);
        System.out.println();

        printArr(arr_2);
        fillArrIncreasingByThree(arr_2);
        printArr(arr_2);
        System.out.println();

        printArr(mas);
        fixArrNumbersLessThanSix(mas);
        printArr(mas);
        System.out.println();

        System.out.println("Max and min from last array:");
        printArr(findMaxAndMin(mas));
        System.out.println();

        System.out.println(calculator());
    }

    static void reversArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 1 ? 0 : 1;
        }
    }

    static void fillArrIncreasingByThree(int[] arr) {
        for (int i = 0, j = 1; i < arr.length; i++, j += 3) {
            arr[i] = j;
        }
    }

    static void fixArrNumbersLessThanSix(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] < 6 ? arr[i] * 2 : arr[i];
        }
    }

    static int[] findMaxAndMin(int[] arr){
        int[] result = {arr[0], arr[0]};
        for (int i = 0; i < arr.length; i++) {
            result[1] = result[1] >  arr[i] ? arr[i] : result[1];
            result[0] = result[0] < arr[i] ? arr[i] : result[0];
        }
        return result;
    }

    static String calculator() throws IOException {
        System.out.print("Enter expression: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ourExpression = reader.readLine();
        int result = 0;
        String[] ourExpressionArray = ourExpression.split(" ");

        switch (ourExpressionArray[1]){
            case "+":
                result = Integer.parseInt(ourExpressionArray[0]) + Integer.parseInt(ourExpressionArray[2]);
                break;
            case "-":
                result = Integer.parseInt(ourExpressionArray[0]) - Integer.parseInt(ourExpressionArray[2]);
                break;
            case "*":
                result = Integer.parseInt(ourExpressionArray[0]) * Integer.parseInt(ourExpressionArray[2]);
                break;
            case "/":
                result = Integer.parseInt(ourExpressionArray[0]) / Integer.parseInt(ourExpressionArray[2]);
                break;
            default:
                System.out.println("Missing operator. Use +, -, *, /.");
        }
        return "result is " + result;
    }

    static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
