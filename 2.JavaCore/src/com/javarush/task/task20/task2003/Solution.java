package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* 
Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполни карту properties данными из файла.
Про .properties почитать тут — http://ru.wikipedia.org/wiki/.properties
Реализуй логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        reader.close();
        FileInputStream fileInputStream = new FileInputStream(name);
        load(fileInputStream);


    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        PrintWriter printWriter = new PrintWriter(outputStream);
        Properties prop = new Properties();
        for (Map.Entry<String, String> pair :
                properties.entrySet()) {
            prop.setProperty(pair.getKey(), pair.getValue());
        }
        prop.store(printWriter, "");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties prop = new Properties();
        prop.load(inputStream);
        Set<String> listNames = prop.stringPropertyNames();
        for (String s :
                listNames) {
            properties.put(s, prop.getProperty(s));
        }
    }

    public static void main(String[] args) {
        for (Map.Entry<String, String> pair :
                properties.entrySet()) {
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }

    }
}
