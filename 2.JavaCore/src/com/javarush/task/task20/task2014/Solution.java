package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/* 
Serializable Solution
Serializable Solution
Сериал

Сериализуй класс Solution.
Подумай, какие поля не нужно сериализовать, пометь ненужные поля модификатором transient.
Объект всегда должен содержать актуальные итоговые данные.
Метод main не участвует в тестировании.

Написать код проверки самостоятельно в методе main:
1) создать файл, открыть поток на чтение (input stream) и на запись(output stream);
2) создать экземпляр класса Solution — savedObject;
3) записать в поток на запись savedObject (убедитесь, что они там действительно есть);
4) создать другой экземпляр класса Solution с другим параметром;
5) загрузить из потока на чтение объект — loadedObject;
6) проверить, что savedObject.string равна loadedObject.string;
7) обработать исключения.
*/
public class Solution implements Serializable{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String name = "D:\\1\\5.txt";
        FileOutputStream  fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        FileInputStream fin = new FileInputStream(name);
        ObjectInputStream ois = new ObjectInputStream(fin);

        Solution saveObject = new Solution(1);
        // сохранем
        oos.writeObject(saveObject);
        fos.close();
        oos.close();

        Solution saveObject1 = new Solution(200);
        Object object= ois.readObject();
        fin.close();
        ois.close();
        saveObject1 = (Solution) object;
        saveObject1.temperature = 2000;


        System.out.println(new Solution(4));
        System.out.println(saveObject);
        System.out.println(saveObject1);
        System.out.println(saveObject1.temperature);
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
