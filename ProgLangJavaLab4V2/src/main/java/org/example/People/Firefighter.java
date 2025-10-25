package org.example.People;

public class Firefighter extends Human {
    public Firefighter(String name, String surname, int age) {
        super(name, surname, age);
    }

    @Override
    public String toString() {
        return "Пожежник: " + super.toString();
    }
}
