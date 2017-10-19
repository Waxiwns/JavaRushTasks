package com.javarush.task.task20.task2017;

import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуй объект в методе getOriginalObject так, чтобы в случае возникновения исключения было выведено сообщение на экран и возвращен null.
Реализуй интерфейс Serializable где необходимо.
*/
public class Solution implements Serializable{
    public A getOriginalObject(ObjectInputStream objectStream){
        try {
            Object object = objectStream.readObject();
            if (object instanceof B)
                return (B) object;
            else if (object instanceof A)
                return (A) object;
            else return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ошибка");
            return null;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("ошибка1");
//            return null;
        }
    }

    public class A implements Serializable{
        public A(){}
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
