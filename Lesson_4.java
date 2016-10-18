//package java_1.lesson_4;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Валентин Фалин on 17.10.2016.
 * Урок 4. ООП. И TicTacToe в ООП без умного AI Бота.
 * Ну и решил оставить блок с остановкой потока для имитации раздумья бота. С ним мне как-то визуально больше нравится процесс игры. 
 * Он просто для красоты. Этот блок можно закомментить, если не нужен.
 */
class Lesson_4 {
    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        persArray[1] = new Person("Sergeev Sergey", "Engineer", "sesergey@mailbox.com", "892312313", 20000, 20);
        persArray[2] = new Person("Petrov Petr", "Engineer", "pepetrov@mailbox.com", "892312314", 40000, 41);
        persArray[3] = new Person("Borisiv Boris", "Engineer", "boboris@mailbox.com", "892312315", 15000, 15);
        persArray[4] = new Person("Alexeev Alexey", "Engineer", "alalexey@mailbox.com", "892312316", 50000, 50);
        System.out.println("All persons:");
        for (Person p: persArray) {
            System.out.println(p);
        }
        System.out.println("Persons older then 40:");
        for (Person p : persArray) {
            if (p.getAge() > 40) System.out.println(p);
        }
        System.out.println("TicTacToe:");
        new TicTacToe().go();
    }
}

class Person {
    private String fullName;
    private String post;
    private String email;
    private String phone;
    private int salary;
    private int age;

    Person(String fullName, String post, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person {" + "fullName = '" + fullName + '\'' + ", post = '" + post + '\'' + ", email = '" + email + '\'' +
                ", phone = '" + phone + '\'' + ", salary = " + salary + ", age = " + age + '}';
    }
}

class TicTacToe {

    final char PLAYER_DOT = 'x';
    final char AI_DOT = 'o';
    final char EMPTY_DOT = '.';
    final int FIELD_SIZE = 3;
    Dot[][] dotField = new Dot[FIELD_SIZE][FIELD_SIZE];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

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
            System.out.println("Enter coordinates X Y (1-" + FIELD_SIZE + "):");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellEmpty(y, x));
        dotField[y][x].setType(PLAYER_DOT);
    }

    void turnAI() {
        int x, y;
        do {
            x = rand.nextInt(FIELD_SIZE);
            y = rand.nextInt(FIELD_SIZE);
        } while (!isCellEmpty(x, y));
        dotField[x][y].setType(AI_DOT);
    }

    boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1) return false;
        if (dotField[x][y].getType() == EMPTY_DOT) return true;
        return false;
    }

    boolean isFieldFull() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (dotField[i][j].getType() == EMPTY_DOT) return false;
        return true;
    }

    boolean checkWin(char ch) {
        // check horizontals
        if (dotField[0][0].getType() == ch && dotField[0][1].getType() == ch && dotField[0][2].getType() == ch) return true;
        if (dotField[1][0].getType() == ch && dotField[1][1].getType() == ch && dotField[1][2].getType() == ch) return true;
        if (dotField[2][0].getType() == ch && dotField[2][1].getType() == ch && dotField[2][2].getType() == ch) return true;
        // check verticals
        if (dotField[0][0].getType() == ch && dotField[1][0].getType() == ch && dotField[2][0].getType() == ch) return true;
        if (dotField[0][1].getType() == ch && dotField[1][1].getType() == ch && dotField[2][1].getType() == ch) return true;
        if (dotField[0][2].getType() == ch && dotField[1][2].getType() == ch && dotField[2][2].getType() == ch) return true;
        // check diag
        if (dotField[0][0].getType() == ch && dotField[1][1].getType() == ch && dotField[2][2].getType() == ch) return true;
        if (dotField[2][0].getType() == ch && dotField[1][1].getType() == ch && dotField[0][2].getType() == ch) return true;
        return false;
    }

    void initField() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                dotField[i][j] = new Dot(new Point(j, i));
    }

    void printField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++)
                System.out.print(dotField[i][j]);
            System.out.println();
        }
    }
}

class Dot {
    char type = '.';
    Point position;

    Dot(Point position) {
        this.position = position;
    }

    void setType(char type) {
        this.type = type;
    }

    char getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{ " + type + " | " + ((int) position.getX() + 1) + ":" + ((int) position.getY() + 1) + '}';
    }
}

