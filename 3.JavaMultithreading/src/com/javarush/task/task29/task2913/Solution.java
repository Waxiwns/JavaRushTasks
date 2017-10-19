package com.javarush.task.task29.task2913;

import java.util.Random;

/*
Замена рекурсии
В программе случайным образом генерируются два целых числа A и В.
Нужно вывести все целые числа от A до B включительно, в порядке возрастания, если A меньше B, или в порядке убывания в противном случае.

Задача реализована с использованием рекурсии.
Иногда в результате работы программы получаем Exception in thread «main« java.lang.StackOverflowError.

Твоя задача: перепиши код без использования рекурсии.
Метод getAllNumbersBetween переименуй на getAllNumbersBetween.
*/

public class Solution {
    private static int numberA;
    private static int numberB;

//    public static String getAllNumbersBetween(int a, int b) {
//        if (a > b) {
//            return a + " " + getAllNumbersBetween(a - 1, b);
//        } else {
//            if (a == b) {
//                return Integer.toString(a);
//            }
//            return a + " " + getAllNumbersBetween(a + 1, b);
//        }
//    }

    public static String getAllNumbersBetween(int a, int b) {
        String s = "";
        if (a < b) {
            for (int i = a; i <= b; i++) {
                s += Integer.toString(i) + " ";
            }
        }
        else  if (a > b){
            for (int i = a; i >= b ; i--) {
                s += Integer.toString(i) + " ";
            }
        }
        else if (a == b)
            s = Integer.toString(a);
        s = s.trim();
        return s;
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}