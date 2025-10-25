// Simple task 4.1 Реалізувати із застосуванням узагальненого програмування ієрархію Java-класів
// для транспортних засобів, які можуть перевозити різні типи пасажирів.

package org.example;

import org.example.Cars.*;
import org.example.People.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Road road = new Road();

        // Автобус може перевозити будь-яких пасажирів
        System.out.println("========== Автобус ==========");
        Bus bus = new Bus(30);
        bus.addPassenger(new Human("Олег", "Петров", 30));
        bus.addPassenger(new Policeman("Іван", "Петров", 35));
        bus.addPassenger(new Firefighter("Петро", "Петров", 40));
        System.out.println("Зайнято місць в автобусі: " + bus.getOccupiedSeats() +
                "/" + bus.getMaxQuantityOfSeats() + "\n");

        // Таксі може перевозити будь-яких пасажирів
        System.out.println("========== Таксі ==========");
        Taxi taxi = new Taxi(5);
        taxi.addPassenger(new Firefighter("Анастасія", "Петрова", 22));
        taxi.addPassenger(new Policeman("Марія", "Петрова", 23));
        taxi.addPassenger(new Human("Софія", "Петрова", 25));
        System.out.println("Зайнято місць в таксі: " + taxi.getOccupiedSeats() +
                "/" + taxi.getMaxQuantityOfSeats() + "\n");

        // Пожежна машина - тільки пожежників
        System.out.println("========== Пожежна машина ==========");
        FireTruck fireTruck = new FireTruck(10);
        fireTruck.addPassenger(new Firefighter("Сергій", "Петров", 30));
        fireTruck.addPassenger(new Firefighter("Антон", "Петров", 33));
        System.out.println("Зайнято місць в пожежній машині: " + fireTruck.getOccupiedSeats() + "/" +
                fireTruck.getMaxQuantityOfSeats() + "\n");

        // Поліцейська машина - тільки поліцейських
        System.out.println("========== Поліцейська машина ==========");
        PoliceCar policeCar = new PoliceCar(5);
        policeCar.addPassenger(new Policeman("Дмитро", "Петров", 40));
        policeCar.addPassenger(new Policeman("Олександр", "Петров", 40));
        System.out.println("Зайнято місць в поліцейській машині: " + policeCar.getOccupiedSeats() + "/" +
                policeCar.getMaxQuantityOfSeats() + "\n");

        // Додаємо всі транспортні засоби на дорогу
        System.out.println("========== Дорога ==========");
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        System.out.println("\nЛюдей на дорозі: " + road.getCountOfHumans() + "\n");

        // Висадка пасажира з таксі
        System.out.println("========== Висадка пасажира з таксі ==========");
        taxi.removePassenger(new Human("Софія", "Петрова", 25));
        System.out.println("Зайнято місць в таксі: " + taxi.getOccupiedSeats() +
                "/" + taxi.getMaxQuantityOfSeats() + "\n");
        taxi.getPassengers();
    }
}