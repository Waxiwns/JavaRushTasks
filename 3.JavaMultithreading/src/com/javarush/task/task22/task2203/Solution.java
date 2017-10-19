package com.javarush.task.task22.task2203;

/* 
Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
//        System.out.println(string.split("\t").length);
        if (string ==null || string.isEmpty() || string.split("\t").length < 3)
            throw new TooShortStringException();

        else {
            return string.substring(string.indexOf("\t")+1, string.indexOf("\t", string.indexOf("\t")+1));
        }
//        return null;
    }

    public static class TooShortStringException extends Exception {
        public TooShortStringException(){
            System.out.println(" )))))))))))))))))))))");
        }
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
