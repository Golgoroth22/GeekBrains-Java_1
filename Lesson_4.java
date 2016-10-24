//package java_1.lesson_4;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Валентин Фалин on 17.10.2016.
 * Урок 4. Введение в ООП.
 * Задание:
 * 1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст; 
 * конструктор класса должен заполнять эти поля при создании объекта; 
 * внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль; 
 * создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
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

