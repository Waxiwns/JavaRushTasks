package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/*
Конвертер систем счислений

Реализуй логику метода convertNumberToOtherNumerationSystem, который должен переводить число number.getDigit(),
из одной системы счисления (numerationSystem) в другую (expectedNumerationSystem).
Брось NumberFormatException, если переданное число некорректно, например, число «120» с системой счисления 2.
Валидация для — number.getDigit() — целое не отрицательное.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110
        number = new Number(NumerationSystemType._16, "6df");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
        System.out.println(result);    //expected 3337
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
        //напишите тут ваш код
        String digit = number.getDigit();
        int syst = number.getNumerationSystem().getNumerationSystemIntValue();
        int syst2 = expectedNumerationSystem.getNumerationSystemIntValue();
        BigInteger bI = new BigInteger(digit, syst);


        return new Number(expectedNumerationSystem, bI.toString(syst2));
    }
}
