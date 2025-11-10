package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager implements Serializable {
    // Вікно вибору файлу
    public static File chooseFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Оберіть файл", FileDialog.LOAD);
        dialog.setVisible(true);

        String directory = dialog.getDirectory();
        String filename = dialog.getFile();

        if (directory == null || filename == null) {
            return null;
        }

        return new File(directory, filename);
    }


    // 1. Пошук рядка з максимальною кількістю слів із заданого файлу
    public static void maxWords(File selectedFile) {
        String maxLine = null;
        int maxWords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;

                String[] words = trimmed.split("\\s+");
                if (words.length > maxWords) {
                    maxWords = words.length;
                    maxLine = line;
                }
            }
            if (maxLine != null) {
                System.out.println("Рядок з найбільшою кількістю слів (" + maxWords + "):");
                System.out.println(maxLine);

                File outputFile = saveToFile("результату maxWords");
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile))) {
                    oos.writeObject("Рядок: " + maxLine + "\nКількість слів: " + maxWords);
                    System.out.println("Результат збережено у файл " + outputFile.getAbsolutePath());

//                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
//                    writer.write("Рядок: " + maxLine + "\nКількість слів: " + maxWords);
//                    System.out.println("Результат збережено у файл " + outputFile.getAbsolutePath());
                }
            } else {
                System.out.println("Файл порожній або немає слів.");
            }

        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }


    // Метод шифрування
    public static void encrypt(File inputFile, char key) {
        File outputFile = saveToFile("зашифрованого файлу");

        try (
                FilterReader reader = new FilterReader(new FileReader(inputFile)) {
                    @Override
                    public int read() throws IOException {
                        int c = super.read();
                        if (c == -1) return -1;
                        return c + key;
                    }
                };

                FilterWriter writer = new FilterWriter(new FileWriter(outputFile)) {
                    @Override
                    public void write(int c) throws IOException {
                        super.write(c);
                    }
                }) {

            System.out.println("Значення ключа " + key + " = " + (int) key);

            int data;
            while ((data = reader.read()) != -1) {
                writer.write(data);
            }

            System.out.println("Файл зашифровано: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }


    // Метод дешифрування
    public static void decrypt(File inputFile, char key) {
        File outputFile = saveToFile("дешифрованого файлу");

        try (FilterReader reader = new FilterReader(new FileReader(inputFile)) {
            @Override
            public int read() throws IOException {
                int c = super.read();
                if (c == -1) return -1;
                return c - key;
            }
        };
             FilterWriter writer = new FilterWriter(new FileWriter(outputFile)) {
                 @Override
                 public void write(int c) throws IOException {
                     super.write(c);
                 }
             }) {

            System.out.println("Значення ключа " + key + " = " + (int) key);

            int data;
            while ((data = reader.read()) != -1) {
                writer.write(data);
            }

            System.out.println("Файл розшифровано");

        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }


    // Підрахунок частоти, з якою зустрічаються теги на сторінці по URL
    public static void tagCounter(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Map<String, Integer> tagCount = new HashMap<>();

            for (Element element : document.getAllElements()) {
                String tag = element.tagName();
                tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
            }

            System.out.println("\nПідрахунок частоти, з якою зустрічаються теги на сторінці по URL: " + url);

            System.out.println("a. Результати в порядку зростання тегів за алфавітом");
            tagCount.entrySet().stream().sorted(Map.Entry.comparingByKey()).
                    forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            System.out.println("\nb. Результати в порядку зростання кількості тегів");
            tagCount.entrySet().stream().sorted(Map.Entry.comparingByValue()).
                    forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            File outputFile = saveToFile("результату tagCounter");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile))) {
                oos.writeObject(tagCount);
                System.out.println("Результат підрахунку тегів збережено у файл " + outputFile.getAbsolutePath());

//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
//                writer.write("Підрахунок частоти тегів на сторінці: " + url + "\n");
//                writer.write("a. За алфавітом:\n");
//                tagCount.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
//                    try {
//                        writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
//                    } catch (IOException e) {
//                        System.err.println("Помилка: " + e.getMessage());
//                    }
//                });
//
//                writer.write("\nb. За кількістю:\n");
//                tagCount.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> {
//                    try {
//                        writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
//                    } catch (IOException e) {
//                        System.err.println("Помилка: " + e.getMessage());
//                    }
//                });
//
//                System.out.println("Результат підрахунку тегів збережено у файл: " + outputFile.getAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }

    public static File saveToFile(String description) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Збереження " + description);

        System.out.print("Введіть назву файлу з розширенням: ");
        String fileName = sc.nextLine();

        System.out.print("Введіть шлях для збереження: ");
        String filePath = sc.nextLine();

        return new File(filePath, fileName);
    }

}
