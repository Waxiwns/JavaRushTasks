package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.

Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}

Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Iv1", "1");
        map.put("Iv2", "2");
        map.put("Iv3", null);
        map.put("Iv4", "4");
        map.put("Iv5", null);
        System.out.println(getQuery(map));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder sb= new StringBuilder();
        for (Map.Entry<String, String> pair :
                params.entrySet()) {
            if (pair.getValue() != null){
//                String s1 = pair.getKey().substring(1, pair.getKey().length()-1);
//                String s2 = pair.getValue().substring(1, pair.getValue().length()-1);
                sb.append(pair.getKey() + " = '" + pair.getValue() + "' and ");
//                sb.append(pair.getKey() + " = " + pair.getValue() + " and ");
            }
        }
        if (sb.length() > 0)
        sb = sb.replace(sb.length()-5, sb.length(), "");
        return sb.toString();
    }

}
