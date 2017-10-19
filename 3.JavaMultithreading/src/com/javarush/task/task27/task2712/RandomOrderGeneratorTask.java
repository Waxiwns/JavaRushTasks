package com.javarush.task.task27.task2712;

import java.util.List;

/**
 * Created by Max on 08.09.2017.
 */

// Создает несколько планшетов, которые будут рандомно генерировать заказы
public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets; // Список планшетов
    private int interval; // интервал создания заказов

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (tablets.size() > 0) {
            while (!Thread.currentThread().isInterrupted()) {
                int randomTab = (int) (Math.random() * (tablets.size()));
                Tablet tablet = tablets.get(randomTab);
//                Order order = tablet.createTestOrder();
                tablet.createTestOrder();
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
