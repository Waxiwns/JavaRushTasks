package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by Max on 31.08.2017.
 */

// Класс отвечающий за ЗАКАЗ
public class Order {
    private final Tablet tablet;    // Планшет
    protected List<Dish> dishes;    // Список выбранных блюд

    // конструктор класса
    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
//        dishes = ConsoleHelper.getAllDishesForOrder();
        initDishes();
        ConsoleHelper.writeMessage(toString());
    }

    @Override
    public String toString() {
        if (dishes== null) {
            return "";
        }
        else {
            return "Your order:" + dishes + " of " + tablet.toString();
        }
    }

    // посчитывает суммарное время приготовления всех блюд в заказе
    public int getTotalCookingTime(){
        int time = 0;
        for (int i = 0; i < dishes.size(); i++) {
            time += dishes.get(i).getDuration();
        }
        return time;
    }

    // Проверяет пустой ли заказ
    public boolean isEmpty(){
        boolean empt = true;
        if (dishes.size() != 0)
            empt = false;
        return empt;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
