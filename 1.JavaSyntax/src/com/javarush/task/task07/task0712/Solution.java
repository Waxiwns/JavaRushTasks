//package com.javarush.task.task07.task0712;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class Solution {
//    public static void main(String[] args) throws Exception {
//        //напишите тут ваш код
//        String[] list = new String[10];
////        List<String> list = new ArrayList<>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        for (int i = 0; i < 10; i++) {
//            list[i] = reader.readLine();
//        }
//        String smin = list[0];
//        String smax = list[0];
//        for (int i = 0; i < list.length; i++) {
////            smin = list.get(0);
////            smax = list.get(0);
//
//            if (list[i].length() < smin.length())
//                smin = list[i];
//            if (list[i].length() > smax.length())
//                smax = list[i];
//        }
//        for (int i = 0; i < list.length; i++) {
//            if (list[i].equals(smin) || list[i].equals(smax)){
//                System.out.println(list[i]);
//                break;
//            }
//
//        }
//    }
//}




package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            list.add(reader.readLine());
        }
        String smin = list.get(0);
        String smax = list.get(0);
        for (int i = 0; i < list.size(); i++) {
//            smin = list.get(0);
//            smax = list.get(0);

            if (list.get(i).length() < smin.length())
                smin = list.get(i);
            if (list.get(i).length() > smax.length())
                smax = list.get(i);
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(smin) || list.get(i).equals(smax)){
                System.out.println(list.get(i));
                break;
            }

        }
    }
}
