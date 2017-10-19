package com.javarush.task.task22.task2211;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* 
Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.

*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
//        String name = args[0];
//        String name2 = args[1];
        String name = "D:\\1\\1.txt";
        String name2 = "D:\\1\\2.txt";
        BufferedReader reader = new BufferedReader(new FileReader(name));
        FileWriter fileWriter = new FileWriter(name2);
        String s = "";
        while (reader.ready()){
            s = reader.readLine();
            String newString = new String(s.getBytes("Windows-1251"), "UTF-8");
            fileWriter.write(newString);
        }
        reader.close();
        fileWriter.close();
    }
}
