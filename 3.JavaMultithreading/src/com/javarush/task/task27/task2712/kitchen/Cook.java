package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Max on 31.08.2017.
 */

// Повар - он будет готовить
public class Cook extends  Observable implements Runnable{
    private String name;
    private boolean busy; // занят повар или нет
    private LinkedBlockingQueue<Order> queue;




    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
//        return "Cook{" +
//                "name='" + name + '\'' +
//                '}';
        return name;
    }

    //— observable — объект, который отправил нам значение
    //— arg — само значение, в нашем случае — это объект Order

    public void startCookingOrder(Order order) {
        busy = true;
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getDishes().toString(), name, order.getTotalCookingTime()*60, order.getDishes()));
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(order);
        try {
            Thread.sleep(order.getTotalCookingTime()*10);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                startCookingOrder(queue.take());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
