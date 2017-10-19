package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;/*
Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) — для чисел (дробные числа разделяются точкой)
alert(String value) — для строк
Обрати внимание на то, что метод alert необходимо вызывать ПОСЛЕ вывода списка всех параметров на экран.

Пример 1

Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo

Вывод:
lvl view name

Пример 2

Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo

Вывод:
obj name
double 3.14
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int vopros = s.indexOf("?") + 1;
        s = s.substring(vopros, s.length());
        String[] strings = s.split("&");
        String s1 = "";
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].indexOf("=") != -1)
                s1 += strings[i].substring(0, strings[i].indexOf("=")) + " ";
            else s1 += strings[i]+ " ";
        }
        System.out.println(s1);

        // Определяем параметр OBJ
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains("obj=")){
                try{
                    alert(Double.parseDouble(strings[i].substring((strings[i].indexOf("=")+1), strings[i].length())));
                }
                catch (Exception e){
                    alert(strings[i].substring((strings[i].indexOf("=")+1), strings[i].length()));
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
