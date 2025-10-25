package org.example;

import org.example.Cars.*;
import org.example.People.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testBus() throws Exception {
        Bus bus = new Bus(30);

        Human h1 = new Human("Олег", "Петров", 30);
        Policeman p1 = new Policeman("Іван", "Петров", 35);
        Firefighter f1 = new Firefighter("Петро", "Петров", 40);

        // Посадка пасажирів
        bus.addPassenger(h1);
        bus.addPassenger(p1);
        bus.addPassenger(f1);

        assertEquals(3, bus.getOccupiedSeats());
        assertEquals(27, bus.getAvailableSeats());
        assertEquals(30, bus.getMaxQuantityOfSeats());
    }

    @Test
    void testTaxi() throws Exception {
        Taxi taxi = new Taxi(5);

        Human h1 = new Human("Софія", "Петрова", 25);
        Policeman p1 = new Policeman("Марія", "Петрова", 23);
        Firefighter f1 = new Firefighter("Анастасія", "Петрова", 22);

        taxi.addPassenger(h1);
        taxi.addPassenger(p1);
        taxi.addPassenger(f1);

        assertEquals(3, taxi.getOccupiedSeats());
        assertEquals(2, taxi.getAvailableSeats());
        assertEquals(5, taxi.getMaxQuantityOfSeats());
    }

    @Test
    void testFireTruck() throws Exception {
        FireTruck fireTruck = new FireTruck(2);

        Firefighter f1 = new Firefighter("Сергій", "Петров", 30);
        Firefighter f2 = new Firefighter("Антон", "Іванов", 28);

        fireTruck.addPassenger(f1);
        fireTruck.addPassenger(f2);

        assertEquals(2, fireTruck.getOccupiedSeats());
        assertEquals(0, fireTruck.getAvailableSeats());
        assertEquals(2, fireTruck.getMaxQuantityOfSeats());

//         fireTruck.addPassenger(new Human("Тест", "Тест", 25));
//         fireTruck.addPassenger(new Policeman("Тест", "Тест", 30));
    }

    @Test
    void testPoliceCar() throws Exception {
        PoliceCar policeCar = new PoliceCar(5);

        Policeman p1 = new Policeman("Дмитро", "Петров", 40);
        Policeman p2 = new Policeman("Олександр", "Сидоров", 35);

        policeCar.addPassenger(p1);
        policeCar.addPassenger(p2);

        assertEquals(2, policeCar.getOccupiedSeats());
        assertEquals(3, policeCar.getAvailableSeats());
        assertEquals(5, policeCar.getMaxQuantityOfSeats());

//         policeCar.addPassenger(new Human("Тест", "Тест", 25));
//         policeCar.addPassenger(new Firefighter("Тест", "Тест", 30));
    }

    @Test
    void testRemovePassenger() throws Exception {
        Taxi taxi = new Taxi(5);

        Human passenger = new Human("Анна", "Коваль", 27);
        taxi.addPassenger(passenger);
        assertEquals(1, taxi.getOccupiedSeats());

        // Висадка існуючого пасажира
        taxi.removePassenger(passenger);
        assertEquals(0, taxi.getOccupiedSeats());
    }

    @Test
    void testRemoveNonExistentPassenger() throws Exception {
        Taxi taxi = new Taxi(5);

        Human passenger1 = new Human("Іван", "Іванов", 30);
        Human passenger2 = new Human("Петро", "Петров", 25);

        taxi.addPassenger(passenger1);

        // Спроба висадити пасажира, якого немає
        Exception exception = assertThrows(Exception.class, () ->
                taxi.removePassenger(passenger2));
        assertTrue(exception.getMessage().contains("не сидить"));
    }

    @Test
    void testNoFreeSeatsException() throws Exception {
        Bus bus = new Bus(2);

        bus.addPassenger(new Human("Тест1", "Тест1", 21));
        bus.addPassenger(new Human("Тест2", "Тест2", 22));

        // Третього пасажира не можна додати
        Exception exception = assertThrows(Exception.class, () ->
                bus.addPassenger(new Human("Тест3", "Тест3", 23)));
        assertTrue(exception.getMessage().contains("Немає вільних місць"));
    }

    @Test
    void testRoad() throws Exception {
        Road road = new Road();

        Bus bus = new Bus(10);
        bus.addPassenger(new Human("Іван", "Іванов", 30));
        bus.addPassenger(new Policeman("Олег", "Петров", 35));

        Taxi taxi = new Taxi(5);
        taxi.addPassenger(new Human("Анна", "Коваль", 25));

        FireTruck truck = new FireTruck(5);
        truck.addPassenger(new Firefighter("Сергій", "Сидоров", 30));

        PoliceCar policeCar = new PoliceCar(5);
        policeCar.addPassenger(new Policeman("Дмитро", "Петров", 40));

        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(truck);
        road.addCarToRoad(policeCar);

        assertEquals(5, road.getCountOfHumans());
    }
}
