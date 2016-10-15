//package com.java_1.lesson_3;

/**
 * Created by Сергей Ирюпин on 11.10.2016. Updated by Валентин Фалин on 12.10.2016
 * Урок 3. TicTacToe Game
 * Получилось расширить поле до 5х5 и установить серию дляток для победы равной 4. Но при этом ситуация хх.хх тоже засчитывается за победную.
 * Эту ошибку пока не придумал как обработать, кроме как ввести дополнительный метод для этого.
 * Получилось сделать проверку ячеек по горизонтали и вертикали с помощью цикла, по диагонали пока не получается.
 * Получилось настроить бота на блокировку пользователя, но тоже только по горизонтали и вертикали при серии у пользователя равной 3.
 * Ну и для имитации раздумья бота добавил sleep на основную нить просто для красоты. Этот блок можно закомментить, если не нужен.
 */

import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    final char PLAYER_DOT = 'x';
    final char AI_DOT = 'o';
    final char EMPTY_DOT = '.';
    final int FIELD_SIZE = 5;
    final int STREAK_TO_WIN = 4;
    char[][] field = new char[FIELD_SIZE][FIELD_SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] args) {
        new TicTacToe().go();
    }

    void go() {
        initField();
        printField();
        while (true) {
            turnPlayer();
            printField();
            if (checkWin(PLAYER_DOT)) {
                System.out.println("You won!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry draw...");
                break;
            }
            //Иммитация раздумья Бота
            try {
                System.out.print("AI thinking");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Иммитация раздумья Бота
            turnAI();
            printField();

            if (checkWin(AI_DOT)) {
                System.out.println("AI won!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Sorry draw...");
                break;
            }
        }
    }

    void turnPlayer() {
        int x, y;
        do {
            System.out.println("Enter coordinates Y X (1-" + FIELD_SIZE + "):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellEmpty(x, y));
        field[x][y] = PLAYER_DOT;
    }

    void turnAI() {
        if (checkNearWin(STREAK_TO_WIN - 1, PLAYER_DOT)) {
            blockPlayer(PLAYER_DOT);
        } else {
            setRandomAI_DOT();
        }
    }

    void setRandomAI_DOT() {
        int x, y;
        do {
            x = rand.nextInt(FIELD_SIZE);
            y = rand.nextInt(FIELD_SIZE);
        } while (!isCellEmpty(x, y));
        field[x][y] = AI_DOT;
    }

    boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
        if (field[x][y] == EMPTY_DOT) return true;
        return false;
    }

    boolean isFieldFull() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (field[i][j] == EMPTY_DOT) return false;
        return true;
    }

    boolean checkWin(char ch) {
        boolean result = false;
        result = checkNearWin(STREAK_TO_WIN, ch);
        // check diag up
        if (field[3][0] == ch && field[2][1] == ch && field[1][2] == ch && field[0][3] == ch) result = true;
        if (field[4][0] == ch && field[3][1] == ch && field[2][2] == ch && field[1][3] == ch) result = true;
        if (field[3][1] == ch && field[2][2] == ch && field[1][3] == ch && field[0][4] == ch) result = true;
        if (field[4][1] == ch && field[3][2] == ch && field[2][3] == ch && field[1][4] == ch) result = true;
        // check diag down
        if (field[1][0] == ch && field[2][1] == ch && field[3][2] == ch && field[4][3] == ch) result = true;
        if (field[0][0] == ch && field[1][1] == ch && field[2][2] == ch && field[3][3] == ch) result = true;
        if (field[1][1] == ch && field[2][2] == ch && field[3][3] == ch && field[4][4] == ch) result = true;
        if (field[0][1] == ch && field[1][2] == ch && field[2][3] == ch && field[3][4] == ch) result = true;
        return result;
    }

    boolean checkNearWin(int streak, char ch) {
        boolean result = false;
        //check horizontals
        for (int i = 0; i < FIELD_SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == ch) counter++;
            }
            if (counter == streak) result = true;
        }
        // check verticals
        for (int i = 0; i < FIELD_SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[j][i] == ch) counter++;
            }
            if (counter == streak) result = true;
        }
        return result;
    }

    void blockPlayer(char ch) {
        boolean useRandom = false;
        //check horizontals
        for (int i = 0; i < FIELD_SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == ch) counter++;
            }
            if (counter == STREAK_TO_WIN - 1) {
                if (field[i][1] == EMPTY_DOT) {
                    field[i][1] = AI_DOT;
                } else if (field[i][3] == EMPTY_DOT) {
                    field[i][3] = AI_DOT;
                } else if (field[i][0] == EMPTY_DOT) {
                    field[i][0] = AI_DOT;
                } else if (field[i][4] == EMPTY_DOT) {
                    field[i][4] = AI_DOT;
                } else {
                    useRandom = true;
                }
            }
        }
        // check verticals
        for (int i = 0; i < FIELD_SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[j][i] == ch) counter++;
            }
            if (counter == STREAK_TO_WIN - 1) {
                useRandom = false;
                if (field[3][i] == EMPTY_DOT) {
                    field[3][i] = AI_DOT;
                } else if (field[1][i] == EMPTY_DOT) {
                    field[1][i] = AI_DOT;
                } else if (field[0][i] == EMPTY_DOT) {
                    field[0][i] = AI_DOT;
                } else if (field[3][i] == EMPTY_DOT) {
                    field[4][i] = AI_DOT;
                } else {
                    useRandom = true;
                }
            }
        }
        if (useRandom) {
            setRandomAI_DOT();
        }
    }

    void initField() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                field[i][j] = EMPTY_DOT;
    }

    void printField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++)
                System.out.print(field[i][j]);
            System.out.println();
        }
    }
}