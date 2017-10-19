package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
//        Integer[] array = {13, 8, 15, 5, 17};
//        sort(array);
//
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
//
//

    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        int mediana;
         if (array.length % 2 == 0)
            mediana = (array[(array.length-1) / 2] + array[((array.length-1) / 2) + 1])/2;
        else
            mediana = array[((array.length-2) / 2) + 1];

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1-mediana) - Math.abs(o2 - mediana);
            }
        };
        Arrays.sort(array, comp);
        return array;
    }
}
