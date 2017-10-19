package com.javarush.task.task27.task2705;

/* 
Второй вариант deadlock
В методе secondMethod расставь synchronized блоки так, чтобы при использовании класса Solution нитями образовывался deadlock.
*/
public class Solution {
    private final Object lock = new Object();

    public synchronized void firstMethod() {
        synchronized (lock) {
            doSomething();
        }
    }

    public void secondMethod() {
        synchronized (lock){
            synchronized (this){
                doSomething();
            }
        }
    }

    private void doSomething() {
    }

    public static void main(String[] args) {

    }
}