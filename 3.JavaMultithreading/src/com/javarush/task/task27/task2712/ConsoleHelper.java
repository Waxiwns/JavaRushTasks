package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Max on 31.08.2017.
 */

// Работа с консолью
public class ConsoleHelper {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // вывод message в консоль
    public static void writeMessage(String message){
        System.out.println(message);
    }

    // Чтение строки с консоли
    public static String readString() throws IOException {
        while (true) {
//            try {
                return reader.readLine();
//            } catch (IOException e) {
//                System.out.println("Произошла ошибка ввода . Пожалуйса, повторие попытку");
//                readString();
//            }
        }
    }

    // Просит пльзователя выбрать блюдо и добавляет его в список
    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        String mes = "Выберете блюдо и нажмите Enter. По окончании введите \"exit\".";
        writeMessage(Dish.allDishesToString());     //выводим заказчику список всех блюд
        writeMessage(mes);                          // просим выбрать блюдо
        while (true) {
            String dish = readString();
            if (dish.equals(Dish.Fish.toString()))
                dishes.add(Dish.Fish);
            else if (dish.equals(Dish.Steak.toString()))
                dishes.add(Dish.Steak);
            else if (dish.equals(Dish.Soup.toString()))
                dishes.add(Dish.Soup);
            else if (dish.equals(Dish.Juice.toString()))
                dishes.add(Dish.Juice);
            else if (dish.equals(Dish.Water.toString()))
                dishes.add(Dish.Water);
            else if (dish.equals("exit"))
                break;
            else {
                writeMessage("Извините, такого блюда нет. Выберете что-нибудь из имеющегося");
            }
        }
        return dishes;
    }
}
