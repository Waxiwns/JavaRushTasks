package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Использовать StringBuilder.

Пример содержимого файла
рот тор торт о
о тот тот тот

Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String name = reader.readLine();
//        reader.close();
        String name = "D:\\1\\5.txt";
        BufferedReader fileReader =  new BufferedReader(new InputStreamReader(new FileInputStream(name)));
//        FileReader fileReader = new FileReader(name);
        StringBuilder sb = new StringBuilder();
        List<String> list =  new ArrayList<>();
        List<String> listcopy =  new ArrayList<>();
        while (fileReader.ready()){
            sb.append(fileReader.readLine() + " ");
        }
        fileReader.close();
        System.out.println(sb.toString());
//        String[] str = sb.toString().split(" ");
        list.addAll(Arrays.asList(sb.toString().split(" ")));

        for (int i = 0; i < list.size(); i++) {
            StringBuilder builderI = new StringBuilder(list.get(i));
            String srev = builderI.reverse().toString();
            System.out.println(srev);
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals(srev) && j != i){
                    listcopy.add(list.get(i));
//                    j = 0;
//                    System.out.println(list.get(i));
//                    break;
                }
//                else
//                    j++;
            }
        }
//        for (int i = 0; i < listcopy.size(); i++) {
//            System.out.println(listcopy.get(i));
//        }

//        for (int i = 0; i < str.length; i++) {
////            System.out.println(str[i]);
//            int count = 0;
//            for (int j = 0; j < str.length; j++) {
//                StringBuilder builderI = new StringBuilder(str[i]);
//                String srev = builderI.reverse().toString();
////                int count = 0;
////                System.out.println(str[i]);
//                if (srev.equals(str[j]) ){
//                    System.out.println(str[i]);
//                    count++;
//                    System.out.println(count);
//                }
////                if (str[j].equals(builderI.reverse().toString()) && count == 2) {
////                    list.add(str[i]);
////                    count++;
////                }
//            }
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = i; j < list.size(); j++) {
//                if (!list.get(i).equals(list.get(j))){
//                    Pair pair = new Pair();
//                    pair.first = list.get(i);
//                    pair.second = new StringBuffer(list.get(i)).reverse().toString();
//                    result.add(pair);
//                }
//            }
//        }

//        System.out.println(sb);
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(result.get(i));
//        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
