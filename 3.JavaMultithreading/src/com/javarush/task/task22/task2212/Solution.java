package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        int totalDigit = telNumber.replaceAll("\\D", "").length();

        //Если строка содержит что-то кроме допустимых символов
        if (telNumber.replaceAll("[^0-9\\+\\(\\)\\-]", "").length() != telNumber.length())
            return false;

        //если номер начинается с ‘+‘ и длинна не 12 цифр
        if (telNumber.startsWith("+") && totalDigit != 12)
            return false;

        //если номер начинается с цифры или скобки и длинна не 10 цифр
        if (telNumber.startsWith("[0-9]\\(") && totalDigit != 10)
            return false;


        return true;
    }

    public static void main(String[] args) {

    }
}
