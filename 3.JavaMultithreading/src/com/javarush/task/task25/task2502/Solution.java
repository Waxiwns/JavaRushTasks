package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.List;

/* 
Машину на СТО не повезем!
Инициализируй поле wheels используя данные из loadWheelNamesFromDB.
Выкинь исключение в случае некорректных данных.

Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car(){
            //init wheels here
            wheels = new ArrayList<>();

            for (int i = 0; i < loadWheelNamesFromDB().length; i++) {
//                Wheel wheel = Wheel.valueOf(loadWheelNamesFromDB()[i]);
                wheels.add(Wheel.valueOf(loadWheelNamesFromDB()[i]));
            }
            if (wheels.size() != 4 || loadWheelNamesFromDB()==null)
                throw new IllegalArgumentException();
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        Car car = new Car();

        for (int i = 0; i < car.wheels.size(); i++) {
            System.out.println(car.wheels.get(i));
        }
    }
}
