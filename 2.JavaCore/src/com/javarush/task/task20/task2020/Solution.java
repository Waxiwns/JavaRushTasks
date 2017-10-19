package com.javarush.task.task20.task2020;

import java.io.PrintStream;
import java.util.logging.Logger;
import java.io.*;

/* 
Сериализуйте Person
Сериализуй класс Person стандартным способом. При необходимости добавь некоторым полям модификатор transient.
*/
public class Solution  implements Serializable{

    public static class Person implements Serializable{
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) throws IOException {
        String name = "D:\\1\\5.txt";
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        Person person = new Person();
        oos.writeObject(Person.class);
    }
}
