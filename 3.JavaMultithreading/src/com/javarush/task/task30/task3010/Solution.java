package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigDecimal;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код

        try {
            String line = args[0];
//            String line = "12AS08z";
//            String line = "00";
            BigDecimal bi = null;

            for (int i = 2; i <= 36; i++) {
                try {
                    bi = new BigDecimal(new BigInteger(line, i));
                    System.out.println(i);
                    break;
                } catch (NumberFormatException e) {
                }
            }
            if (bi == null)
                System.out.println("incorrect");

        }catch (Exception e){}

    }
}