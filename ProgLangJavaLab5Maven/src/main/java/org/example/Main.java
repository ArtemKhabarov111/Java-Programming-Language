package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Програма, яка отримає рядок з максимальною кількістю слів із заданого файлу
        File selectedFile = FileManager.chooseFile();
        if (selectedFile != null) {
            System.out.println("Обраний файл: " + selectedFile.getAbsolutePath());
            FileManager.maxWords(selectedFile);
        } else {
            System.out.println("Файл не обрано. Завершення програми.");
        }


        // 3. Зашифрувати вхідний символьний потік за наступним алгоритмом:
        // замінити кожний символ на такий, код якого більший на значення коду ключового символу.
        System.out.print("\nВведіть шлях вхідного файлу для шифрування/дешифрування: ");
        File inputFile = new File(sc.nextLine());

        System.out.print("Введіть ключ (символ): ");
        char key = sc.nextLine().charAt(0);

        System.out.print("Оберіть дію (1 - шифрування, 2 - дешифрування): ");
        int choice;
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            System.err.println("Некоректне введення режиму. Завершення програми.");
            return;
        }

        if (choice == 1) {
            FileManager.encrypt(inputFile, key);
        } else if (choice == 2) {
            FileManager.decrypt(inputFile, key);
        } else {
            System.out.println("Невірний режим. Завершення програми.");
            return;
        }


        // 4. Підрахувати частоту, з якою зустрічаються теги на сторінці по URL
        FileManager.tagCounter("https://en.wikipedia.org/wiki/Java_(programming_language)");
    }
}
