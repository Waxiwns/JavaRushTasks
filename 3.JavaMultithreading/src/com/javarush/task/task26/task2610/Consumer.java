package com.javarush.task.task26.task2610;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Max on 31.07.2017.
 */
public class Consumer implements Runnable{
    private BlockingQueue queue;
    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        int i = 0;
        while (true){
            try {
                System.out.println(queue.take());
//                        put(String.valueOf(i++));
//                System.out.println(queue.take());
                Thread.sleep(300);

            } catch (InterruptedException e) {
                System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
            }
        }
    }
}
