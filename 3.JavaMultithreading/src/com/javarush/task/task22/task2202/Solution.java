//package com.javarush.task.task22.task2202;
//
///*
//Найти подстроку
//Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
//которое следует после 4-го пробела.
//
//Пример:
//«JavaRush — лучший сервис обучения Java.»
//
//Результат:
//«— лучший сервис обучения»
//
//На некорректные данные бросить исключение TooShortStringException (сделать исключением).
//*/
//public class Solution {
//    public static void main(String[] args) {
//        System.out.println(getPartOfString("   JavaRush — лучший сервис обучения Java."));
//    }
//
//    public static String getPartOfString(String string) {
//       if (string !=null){
//        int count = 0;
//        for (int i = 0; i < string.length(); i++) {
//            char sss = string.charAt(i);
//            if (sss == ' ')
//                count++;
//        }
////        System.out.println(count);
//        if (string.isEmpty() || count < 4)
//            throw new TooShortStringException();
//        else {
//            String s = "";
//            String[] str = string.split(" ");
//            for (int i = 0; i < str.length; i++) {
//                if (i>0 && i<5)
//                    s +=str[i] + " ";
//            }
//            s = s.trim();
//            return s;
//        }
//    }
//    else throw new TooShortStringException();
//    }
//
//    public static class TooShortStringException extends RuntimeException {
//        public TooShortStringException(){
//            super();
//        }
//    }
//}


package com.javarush.task.task22.task2202;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.

Пример:
«JavaRush — лучший сервис обучения Java.»

Результат:
«— лучший сервис обучения»

На некорректные данные бросить исключение TooShortStringException (сделать исключением).
*/
public class Solution {
    public static void main(String[] args) {
        String message = "Боб: дата";

        if (message.contains(":")){
            String name, text;
            String[] mesmas = message.split(":", 2);
            name = mesmas[0];
            text = mesmas[1].trim();
            SimpleDateFormat simpleDateFormat;
            System.out.println(name);

            if (text.equals("дата")) simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
            else if (text.equals("день")) simpleDateFormat = new SimpleDateFormat("dd");
            else if (text.equals("месяц")) simpleDateFormat = new SimpleDateFormat("MM");
            else if (text.equals("год")) simpleDateFormat = new SimpleDateFormat("YYYY");
            else if (text.equals("время")) simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            else if (text.equals("часы")) simpleDateFormat = new SimpleDateFormat("HH");
            else if (text.equals("минуты")) simpleDateFormat = new SimpleDateFormat("mm");
            else if (text.equals("секунды")) simpleDateFormat = new SimpleDateFormat("ss");
            else return;

            String message1 = "Информация для " + name + ": " + simpleDateFormat.format(Calendar.getInstance().getTime());
            System.out.println(message1);
        }
        else  return;
    }
}
