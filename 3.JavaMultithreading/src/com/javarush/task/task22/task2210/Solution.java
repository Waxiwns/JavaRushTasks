package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.

Пример
getTokens("level22.lesson13.task01", ".")

Возвращает
{"level22", "lesson13", "task01"}
*/
public class Solution {
    public static void main(String[] args) {
        String[] s = getTokens("level22.lesson13.task01", ".");
        System.out.println(s.length);
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
//        String s1 = "level22. lesson13. task01";
//        String s2 = "\\.";
//        String[] ss1 = s1.split(s2);
//        System.out.println(ss1.length);
//        for (int i = 0; i < ss1.length; i++) {
//            System.out.println(ss1[i]);
//        }
    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);
        String[] s = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < s.length; i++) {
            s[i] = stringTokenizer.nextToken();
        }

        return s;
    }
}
