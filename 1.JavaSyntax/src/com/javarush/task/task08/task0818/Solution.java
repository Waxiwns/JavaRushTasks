package com.javarush.task.task08.task0818;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
Создать словарь (Map<String, Integer>) и занести в него десять записей по принципу: «фамилия» — «зарплата».
Удалить из словаря всех людей, у которых зарплата ниже 500.
*/

public class Solution {
    public static HashMap<String, Integer> createMap() throws IOException {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap<String, Integer>();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        for (int i = 0; i < 10; i++) {
//            String s = reader.readLine();
//            int j = Integer.parseInt(reader.readLine());
//            map.put(s, j);
//        }
        map.put("qwer1", 5010);
        map.put("qwer2", 5001);
        map.put("qwer3", 5004);
        map.put("qwer4", 5006);
        map.put("qwer5", 5004);
        map.put("qwer6", 50);
        map.put("qwer7", 59);
        map.put("qwer8", 40);
        map.put("qwer9", 400);
        map.put("qwer10", 300);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        HashMap<String, Integer> mapa = new HashMap(map);
        for (Map.Entry<String, Integer> pair :
                mapa.entrySet()) {
            if (pair.getValue() < 500)
                map.remove(pair.getKey());
//                mapa.put(pair.getKey(), pair.getValue());
        }
//        map = mapa;
    }

    public static void main(String[] args) {

    }
}