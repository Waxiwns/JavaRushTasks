package com.javarush.task.task27.task2712.kitchen;

/**
 * Created by Max on 31.08.2017.
 */

// Блюдо
public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);
    private int duration;


    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    //
    public static String allDishesToString() {
        return Fish.toString() + " " + Steak.toString() + " " + Soup.toString() + " " + Juice.toString() + " " + Water.toString();
    }
}
