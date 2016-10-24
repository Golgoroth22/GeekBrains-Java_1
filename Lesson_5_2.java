//package java_1.lesson_5;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Валентин Фалин on 19.10.2016.
 * Урок 5. Часть 2. TicTacToe переработаное в ООП.
 * Задание:
 * 2. * Переписать "Крестики-нолики" в объектно-ориентированном стиле.
 */
class Lesson_5_2 {
    public static void main(String[] args) {
        Lesson_5 l = new Lesson_5();
        l.startTicTacToeGame(new TicTacToeConsolePlayer(), new TicTacToeAI_Bot(), new TicTacToeGameField(3));
    }

    void startTicTacToeGame(TicTacToePlayer player_1, TicTacToePlayer player_2, TicTacToeGameField gameField) {
        System.out.println("TicTacToe started!");
        int[] X_Y;
        TicTacToePlayer player = null; //Текущий ходящий игрок.
        gameField.printField();
		//Игровой цикл
        for (int i = 2; ; i++) {
            if (i % 2 == 0) player = player_1;
            else player = player_2;
            System.out.println(player.getType() + " turn!");
            do {
                X_Y = player.turn(gameField.getFieldSize());
            } while (!gameField.isDotEmpty(X_Y));
            gameField.setDot(X_Y, player);
            gameField.printField();
            if (gameField.checkWin(player.getDot())) {
                System.out.println(player.getType() + " won!");
                break;
            }
            if (gameField.isFieldFull()) {
                System.out.println("Sorry draw...");
                break;
            }
        }
    }
}

//Класс-родитель для игроков
abstract class TicTacToePlayer {
    abstract char getDot();

    abstract int[] turn(int fieldSize);

    abstract String getType();
}

//Игрок-человек
class TicTacToeConsolePlayer extends TicTacToePlayer {
    Scanner scanner = new Scanner(System.in);
    final private char PLAYER_DOT = 'x';

    public TicTacToeConsolePlayer() {
    }

    @Override
    char getDot() {
        return PLAYER_DOT;
    }

    @Override
    int[] turn(int fieldSize) {
        int[] X_Y = new int[2];
        System.out.println("Enter coordinates 1-" + fieldSize + " in format {Y X}:");
        X_Y[0] = scanner.nextInt() - 1;
        X_Y[1] = scanner.nextInt() - 1;
        return X_Y;
    }

    @Override
    String getType() {
        return "You";
    }
}

//Игрок-бот
class TicTacToeAI_Bot extends TicTacToePlayer {
    final char AI_DOT = 'o';

    @Override
    char getDot() {
        return AI_DOT;
    }

    @Override
    int[] turn(int fieldSize) {
        Random random = new Random();
        int[] X_Y = new int[2];
        X_Y[0] = random.nextInt(fieldSize);
        X_Y[1] = random.nextInt(fieldSize);
        return X_Y;
    }

    @Override
    String getType() {
        return "AI_BOT";
    }
}

//Игровое поле
class TicTacToeGameField {
    private final char EMPTY_DOT = '.';
    int fieldSize;
    char[][] gameField;

    TicTacToeGameField(int fieldSize) {
        this.fieldSize = fieldSize;
        gameField = new char[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                gameField[i][j] = EMPTY_DOT;
            }
        }
    }

    int getFieldSize() {
        return fieldSize;
    }

    void setDot(int[] dotCoordinates, TicTacToePlayer player) {
        gameField[dotCoordinates[0]][dotCoordinates[1]] = player.getDot();
    }

    boolean isDotEmpty(int[] X_Y) {
        if (X_Y[0] < 0 || X_Y[1] < 0 || X_Y[0] > fieldSize - 1 || X_Y[1] > fieldSize - 1) return false;
        if (gameField[X_Y[0]][X_Y[1]] == EMPTY_DOT) return true;
        return false;
    }

    void printField() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++)
                System.out.print(gameField[i][j]);
            System.out.println();
        }
    }

    boolean isFieldFull() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (gameField[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    boolean checkWin(char ch) {
        // check horizontals
        if (gameField[0][0] == ch && gameField[0][1] == ch && gameField[0][2] == ch) return true;
        if (gameField[1][0] == ch && gameField[1][1] == ch && gameField[1][2] == ch) return true;
        if (gameField[2][0] == ch && gameField[2][1] == ch && gameField[2][2] == ch) return true;
        // check verticals
        if (gameField[0][0] == ch && gameField[1][0] == ch && gameField[2][0] == ch) return true;
        if (gameField[0][1] == ch && gameField[1][1] == ch && gameField[2][1] == ch) return true;
        if (gameField[0][2] == ch && gameField[1][2] == ch && gameField[2][2] == ch) return true;
        // check diag
        if (gameField[0][0] == ch && gameField[1][1] == ch && gameField[2][2] == ch) return true;
        if (gameField[2][0] == ch && gameField[1][1] == ch && gameField[0][2] == ch) return true;
        return false;
    }
}