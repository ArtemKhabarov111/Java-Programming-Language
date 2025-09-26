package org.example;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        // Екземпляр Person
        Person person1 = new Person("Khabarov", "Artem", 20);

        // Екземпляр Gson
        Gson gson = new Gson();

        // Конвертація в JSON
        String json = gson.toJson(person1);
        System.out.println("toJSON: " + json);

        // Конвертація назад в об'єкт
        Person person2 = gson.fromJson(json, Person.class);
        System.out.println("fromJson: " + person2);

        // Перевірка equals-ом початковий і одержаний об'єкти
        System.out.println("Початковий і одержаний об'єкти рівні: " + person1.equals(person2));

    }
}
