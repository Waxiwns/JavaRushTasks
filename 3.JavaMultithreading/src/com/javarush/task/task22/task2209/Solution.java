package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
Составить цепочку слов
В методе main считай с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставь все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
Вывести полученную строку на экран.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();//"D:\\1\\6.txt"; // reader.readLine();
        BufferedReader fileReader = new BufferedReader(new FileReader(name));
        reader.close();
        StringBuilder sb = new StringBuilder();
        while (fileReader.ready()){
            sb.append(fileReader.readLine());
        }
        fileReader.close();


        StringBuilder result = getLine(sb.toString().split(" "));
        System.out.println(result.toString());

    }

    public static StringBuilder getLine(String... words) {
        StringBuilder strb = new StringBuilder();
        if (words == null || words.length == 0) return strb;
            String[] list = words;
            ArrayList<String> listArr = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                if (list[i].length() > 1 )
                listArr.add(list[i]);

            }
            Collections.sort(listArr);

            strb.append(listArr.get(0) + " ");
            listArr.remove(0);
            int count = listArr.size();
            while (listArr.size() != 0){
                if (count > 0){
                    for (int i = 0; i < listArr.size();) {
                        if (strb.substring(strb.length()-2, strb.length()-1).equals(listArr.get(i).substring(0, 1).toLowerCase())){
                            strb.append(listArr.get(i) + " ");
                            listArr.remove(i);
                        }
                        else
                            i++;
                    }
                    count--;
                }
                if (count <= 0 && listArr.size() > 0){
                    for (int i = 0; i < listArr.size(); i++) {
                        strb.append(listArr.get(i) + " ");
                        listArr.remove(i);
                    }
                }
            }
            return strb.deleteCharAt(strb.length()-1);
//        }

//        else return strb;
    }
}
