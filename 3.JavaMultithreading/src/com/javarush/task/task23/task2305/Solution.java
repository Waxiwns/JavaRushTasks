package com.javarush.task.task23.task2305;

/* 
Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public static class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] solutions = new Solution[2];
        Solution sol1 = new Solution();
        sol1.innerClasses = new InnerClass[2];
        InnerClass innerClass1 = new InnerClass();
        InnerClass innerClass2 = new InnerClass();
        InnerClass innerClass3 = new InnerClass();

        InnerClass innerClass4 = new InnerClass();
        sol1.innerClasses[0] = innerClass1;
        sol1.innerClasses[1] = innerClass2;

        Solution sol2 = new Solution();
        sol2.innerClasses = new InnerClass[2];
        sol2.innerClasses[0] = innerClass3;
        sol2.innerClasses[1] = innerClass4;

        solutions[0] = sol1;
        solutions[1] = sol2;

        return solutions;
    }

    public static void main(String[] args) {

    }
}
