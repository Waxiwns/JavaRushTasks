package com.javarush.task.task20.task2018;

import java.io.Serializable;
import java.io.*;

/* 
Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.

Найди проблему и исправь ее.

Класс A не должен реализовывать интерфейсы Serializable и Externalizable.

В сигнатуре класса В ошибки нет :).

Метод main не участвует в тестировании.
*/
public class Solution implements Serializable {
    public static class A {
//        public A(){}
        protected String name = "A";

        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {
        public void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(name);
        }
        public void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            name = (String) objectInputStream.readObject();
        }
        public B(String name) {
            super(name);
            this.name += name;
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(arrayOutputStream);

        Solution solution = new Solution();
        B b = solution.new B("B2");
        System.out.println(b.name);

        oos.writeObject(b);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(arrayInputStream);

        B b1 = (B)ois.readObject();
        System.out.println(b1.name);
    }
}
