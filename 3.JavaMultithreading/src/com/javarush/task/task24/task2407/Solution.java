package com.javarush.task.task24.task2407;

import java.util.List;

/* 
Реализация интерфейса используя локальный класс
В классе Cat реализуй логику метода toSayable, которая описана в джавадоке.
*/
public class Solution {
    public static void main(String[] args) {
        List<Pet> pet = Util.getPets();
        List<Sayable> pets = Util.convertPetToSayable(pet);
        Util.printDialog(pets);
    }
}
