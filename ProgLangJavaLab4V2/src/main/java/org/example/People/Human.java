package org.example.People;

public class Human {
    private final String name;
    private final String surname;
    private final int age;

    public Human(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Ім'я: " + name + "; Прізвище: " + surname + "; Вік: " + age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Human human = (Human) obj;
        return name.equals(human.name) && surname.equals(human.surname) && age == human.age;
    }
}
