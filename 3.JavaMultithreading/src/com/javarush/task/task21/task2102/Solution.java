package com.javarush.task.task21.task2102;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* 
Сравниваем модификаторы
Реализовать логику метода isAllModifiersContainSpecificModifier, который проверяет,
содержит ли переданный параметр allModifiers значение конкретного модификатора specificModifier.

P.S. Перед выполнением задания ознакомься с классом Modifier и реализацией методов isPublic, isStatic и т.п.
*/
public class Solution {
    public static void main(String[] args) {
        int modifiersOfThisClass = Solution.class.getModifiers();
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfThisClass, Modifier.PUBLIC));   //true
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfThisClass, Modifier.STATIC));   //false

        int modifiersOfMethod = getMainMethod().getModifiers();
        System.out.println(isAllModifiersContainSpecificModifier(modifiersOfMethod, Modifier.STATIC));      //true

//        System.out.println(Modifier.isPublic(modifiersOfMethod));
//        System.out.println(Modifier.isPublic(1));
//        System.out.println(Modifier.isPublic(9));
//        System.out.println(modifiersOfMethod);
    }

    public static boolean isAllModifiersContainSpecificModifier(int allModifiers, int specificModifier) {
//        if (Modifier.isPrivate(allModifiers).)
        if ((allModifiers & specificModifier) == specificModifier)
            return true;
        else return false;
    }

    private static Method getMainMethod() {
        Method[] methods = Solution.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("main")) return method;
        }

        return null;
    }
}
