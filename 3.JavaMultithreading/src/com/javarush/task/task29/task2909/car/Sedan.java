package com.javarush.task.task29.task2909.car;

/**
 * Created by Max on 13.04.2017.
 */
public class Sedan extends Car {
    public Sedan(int type, int numberOfPassengers) {
        super(type, numberOfPassengers);
    }
    public Sedan(int numberOfPassengers){
        super(1, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        int MAX_SEDAN_SPEED = 120;
        return MAX_SEDAN_SPEED;
    }
}
