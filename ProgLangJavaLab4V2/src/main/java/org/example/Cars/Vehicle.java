package org.example.Cars;

import java.util.ArrayList;
import java.util.List;

public class Vehicle<T>{
    private final int maxQuantityOfSeats;
    List<T> passengers = new ArrayList<T>();

    public Vehicle(int maxQuantityOfSeats) {
        if (maxQuantityOfSeats <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути > 0");
        }
        this.maxQuantityOfSeats = maxQuantityOfSeats;
    }

    public int getMaxQuantityOfSeats() {
        return maxQuantityOfSeats;
    }

    public int getOccupiedSeats(){
        return passengers.size();
    }

    public int getAvailableSeats(){
        return maxQuantityOfSeats - passengers.size();
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public void addPassenger(T passenger) throws Exception{
        if (passenger == null) {
            throw new IllegalArgumentException("Пасажир не може бути null");
        }
        if (passengers.size() >= maxQuantityOfSeats) {
            throw new Exception("Немає вільних місць у транспортному засобі");
        }
        passengers.add(passenger);
        System.out.println("Додано пасажира: " + passenger);
    }

    public void removePassenger(T passenger) throws Exception{
        if (passenger == null) {
            throw new IllegalArgumentException("Пасажир не може бути null");
        }
        if (!passengers.contains(passenger)) {
            throw new Exception("Пасажир «не сидить» у транспортному засобі");
        }
        passengers.remove(passenger);
        System.out.println("Висаджено пасажира: " + passenger);
    }

    public void getPassengers(){
        System.out.println("Пасажири, які залишилися: ");
        for (T passenger : passengers) {
            System.out.println(passenger);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                ", Місць: " + getMaxQuantityOfSeats() +
                ", Зайнято: " + getOccupiedSeats() +
                ", Вільно: " + getAvailableSeats();
    }
}
