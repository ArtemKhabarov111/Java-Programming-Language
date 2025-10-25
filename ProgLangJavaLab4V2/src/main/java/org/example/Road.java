package org.example;

import org.example.Cars.Vehicle;
import org.example.People.Human;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans(){
        int count = 0;
        for (Vehicle<? extends Human> vehicle : carsInRoad) {
            count += vehicle.getPassengerCount();
        }
        return count;
    }

    public void addCarToRoad(Vehicle<? extends Human> vehicle){
        if (vehicle == null) {
            throw new IllegalArgumentException("Транспортний засіб не може бути null");
        }
        carsInRoad.add(vehicle);
        System.out.println("На дорогу додано: " + vehicle);
    }
}
