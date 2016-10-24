//package java_1.lesson_6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Валентин Фалин on 24.10.2016.
 * Урок 6. Работа с файлами и строками.
 * Задание:
 * 1. Создать 2 текстовых файла, примерно по 50-100 символов в каждом (особого значения не имеет) и написать программу, «склеивающую» эти файлы, 
 * то есть вначале идет текст из первого файла, потом текст из второго.
 * 2. Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле (Можно использовать файлы из задания 1).
 */
class Lesson_6 {
    public static void main(String[] args) {
        List<String> finalList = new ArrayList<>();
        finalList.addAll(readFromFile(args[0]));
        finalList.addAll(readFromFile(args[1]));
        writeToFile(finalList, args[2]);
        System.out.println(containWordInFile(args[0], "main"));
    }

    static List<String> readFromFile(String fileRath){
        List<String> list = null;
        try {
            list = Files.readAllLines(Paths.get(fileRath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Missing file to read.");
        }
        return list;
    }

    static void writeToFile(List<String> list, String filePath){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Missing file to write.");
        }
        for (String s : list){
            writer.println(s);
        }
        writer.close();
    }

    static boolean containWordInFile(String filePath, String word) {
        List<String> list = readFromFile(filePath);
        for (String s : list){
            if (s.contains(word)) return true;
        }
        return false;
    }
}
