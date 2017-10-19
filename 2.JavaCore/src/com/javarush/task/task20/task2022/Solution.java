package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправь ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.

Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные — writeObject
3) сериализовать класс Solution — writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные — writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable {
    public String fname;
    transient private FileOutputStream stream;

    public Solution(String fileName) throws FileNotFoundException {
        this.fname = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
//        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(this.fname, true);
//        in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution solution = new Solution("D:\\1\\5.txt");
        solution.writeObject("Проверка");


        FileOutputStream fos = new FileOutputStream("D:\\1\\6.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(solution);
        fos.close();
        oos.close();

        FileInputStream fis = new FileInputStream("D:\\1\\6.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        Solution newSolution = (Solution) object;

        fis.close();
        ois.close();
        newSolution.writeObject("Get Away");


    }
}
