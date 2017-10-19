package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max on 08.09.2017.
 */

// Генерация заказа с произвльными блюдами
public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
//        dishes = new ArrayList<Dish>();
        List<Dish> allDish = new ArrayList<Dish>(Arrays.asList(Dish.values()));
        dishes = new ArrayList<Dish>(Arrays.asList(Dish.values()));
        int random = (int) (Math.random() * (dishes.size()));
        Collections.shuffle(dishes);
        if (dishes.size() > random)
            allDish.subList(dishes.size() - random, dishes.size()).clear();
    }
}
