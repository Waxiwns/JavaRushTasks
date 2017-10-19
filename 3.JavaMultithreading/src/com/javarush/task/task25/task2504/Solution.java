package com.javarush.task.task25.task2504;

/*
Switch для нитей
Обработай список нитей в зависимости от состояния:
1. Если нить еще не запущена, то запусти ее.
2. Если нить в ожидании, то прерви ее.
3. Если нить работает, то проверь маркер isInterrupted.
4. Если нить прекратила работу, то выведи в консоль ее приоритет.
Используй switch.
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (int i = 0; i < threads.length; i++) {
            switch (threads[i].getState()){
                case NEW:
                    threads[i].start();
                    break;
                case BLOCKED:
                threads[i].interrupt();
                    break;
                case WAITING:
                    threads[i].interrupt();
                    break;
                case RUNNABLE:
                    threads[i].isInterrupted();
                    break;
                case TIMED_WAITING:
                    threads[i].interrupt();
                    break;
                case TERMINATED:
                    System.out.println(threads[i].getPriority());
                    break;
            }

        }

    }

    public static void main(String[] args) {
    }
}
