package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
Считать с консоли 2 имени файла — file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines.
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME.
В оригинальном и редактируемом файлах пустых строк нет.

Пример:
оригинальный    редактированный    общий
file1:          file2:             результат:(lines)

строка1         строка1            SAME строка1
строка2         REMOVED            строка2
строка3         строка3            SAME строка3
строка4         REMOVED            строка4
строка5         строка5            SAME строка5
строка0         ADDED              строка0
строка1         строка1            SAME строка1
строка2         REMOVED            строка2
строка3         строка3            SAME строка3
строка5         ADDED              строка5
строка4         строка4            SAME строка4
строка5         REMOVED            строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name1 = reader.readLine();
        String name2 = reader.readLine();
        reader.close();
        BufferedReader freader1 = new BufferedReader(new FileReader(name1));
        BufferedReader freader2 = new BufferedReader(new FileReader(name2));

        ArrayList<String> f1 = new ArrayList<>();
        ArrayList<String> f2 = new ArrayList<>();
        String fs1 = "";
        String fs2 = "";

        while (freader1.ready()){
            fs1 = freader1.readLine();
            f1.add(fs1);
            fs2 = freader2.readLine();
            f2.add(fs2);
            if (fs1.equals(fs2))
                lines.add(new LineItem(Type.SAME, fs1));
            else{

            }
            if (!fs1.equals(fs2) || fs2.equals("\\n"))
                lines.add(new LineItem(Type.REMOVED, fs1));
            if (!fs1.equals(fs2))
                lines.add(new LineItem(Type.ADDED, fs1));

        }

        for (int i = 0; i < lines.size(); i++)
        {
            System.out.println(lines.get(i).type + lines.get(i).line);
        }
        System.out.println(f1.size());
        System.out.println(f2.size());

        freader1.close();
        freader2.close();
    }



    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
