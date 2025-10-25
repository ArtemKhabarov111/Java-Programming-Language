package org.example.People;

public class Policeman extends Human {
    public Policeman(String name, String surname, int age) {
        super(name, surname, age);
    }

    @Override
    public String toString() {
        return "Поліцейський: " + super.toString();
    }
}
