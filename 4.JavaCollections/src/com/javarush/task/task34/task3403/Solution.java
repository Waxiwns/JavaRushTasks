package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion(132);
    }
    public void recursion(int n) {
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                if (n / i > 1) {
                    System.out.println(i);
                    recursion(n / i);
                    return;
                }
                else {
                    System.out.println(i);
                    return;
                }
            }
        }
    }
}
