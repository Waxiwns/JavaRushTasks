package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Max on 31.07.2017.
 */
public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map){
        this.map = map;
    }

    @Override
    public void run() {
        try {

            while (true) {
                    int i = 1;
                    String s = "Some text for " + i;
                    map.put(String.valueOf(i), s);
                    Thread.sleep(500);
                    i++;
                }
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " thread was terminated");
        }

    }
}
