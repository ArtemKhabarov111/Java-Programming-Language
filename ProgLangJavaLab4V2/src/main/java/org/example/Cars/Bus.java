package org.example.Cars;

import org.example.People.Human;

public class Bus extends Vehicle<Human> {
    public Bus(int maxQuantityOfSeats) {
        super(maxQuantityOfSeats);
    }
}
