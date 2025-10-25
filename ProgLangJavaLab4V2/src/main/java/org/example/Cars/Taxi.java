package org.example.Cars;

import org.example.People.Human;

public class Taxi extends Vehicle<Human> {
    public Taxi(int maxQuantityOfSeats) {
        super(maxQuantityOfSeats);
    }
}
