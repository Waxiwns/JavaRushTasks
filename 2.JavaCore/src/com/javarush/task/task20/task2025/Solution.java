package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Алгоритмы-числа
Число S состоит из M цифр, например, S=370 и M (количество цифр) = 3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания.

Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8

На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    //Опредиление количества цифр в числе
    public static byte countAmstNum(long N){
        byte i = 0;
        if (N > 0){
            while (N !=0){
                N = N/10;
                i++;
            }
        }


        return i;
    }
    //Является ли число числом Армстронга
    public static boolean isArm(long N){
        int drob = 0;
        long result = 0;
        long orig = N;
        byte count = countAmstNum(N);
        while (N != 0){
            drob = (int) (N % 10);
            result=result+((long) Math.pow(drob, count));
            N=N/10;
        }
        if (orig == result)
            return true;
        return false;
    }



    public static long[] getNumbers(long N) {
      // 1й вариант долгий
        long[] res = null;

        List<Long> list = new ArrayList<>();

        for (long k = 0; k < N; k++) {
            long s = k;

            String stringS = Long.toString(s);
            String[] mas = stringS.split("");
            long result = 0;
            long result1 = 0; // цифра числа

            for (int i = 0; i < mas.length; i++) {
                result1 = Integer.parseInt(mas[i]);
                for (int j = 1; j < mas.length; j++) {      //возводим
                    result1 *= Integer.parseInt(mas[i]);    //в степень (кол-ва цифр в числе)
                }
                if (result1 > k)
                    break;
                else
                result += result1;                          //сумма чисел цифры в степени
            }
            // если цафра равна сумме чисел в степени то добавить в список
            if (result == k){
                list.add(result);
            }

        }

        res = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;

/*
       // 2й вариант

        long[] res = null;
        List<Long> list = new ArrayList<>();
        for (long i = 1; i < N; i++) {
            if (isArm(i)) {
                list.add(i);
            }
        }
        res = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
*/
           }

    public static void main(String[] args) {
//        System.out.println(isArm(372));
//        System.out.println(countAmstNum(1000000000));

//        long[] mas = getNumbers(99999);
//        for (int i = 0; i < mas.length; i++) {
//            System.out.println(mas[i]);
//        }
        long N = 999999;
        for (long i = 0; i < N; i++) {
if (isArm(i))
    System.out.println(i);
        }



//        и память посмотри, так надо
        Date startTime = new Date();
        long timeStart = System.currentTimeMillis();
        long[] test = getNumbers(999999);
        long timeEnd = System.currentTimeMillis();
        Date endTime = new Date();
        long memStart = Runtime.getRuntime().totalMemory();
        long memEnd = Runtime.getRuntime().freeMemory();
        System.out.println(((timeEnd - timeStart) ) + " - "
                + ((endTime.getTime() - startTime.getTime()) ) + " mS");
        System.out.println((memStart - memEnd) / (1024) + " Kb");

    }
}
