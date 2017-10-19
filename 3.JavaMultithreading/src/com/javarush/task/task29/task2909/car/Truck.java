package com.javarush.task.task29.task2909.car;

/**
 * Created by Max on 13.04.2017.
 */
public class Truck extends Car {
    public Truck(int type, int numberOfPassengers) {
        super(type, numberOfPassengers);
    }
    public Truck(int numberOfPassengers){
        super(0, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        int MAX_TRUCK_SPEED = 80;
        return MAX_TRUCK_SPEED;
    }
}
