package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Max on 31.08.2017.
 */

// класс отвечающий за планшет (заказчика)
public class Tablet{
    private final int number;   // это номер планшета, чтобы можно было однозначно установить, откуда поступил заказ
    private static Logger logger = Logger.getLogger(Tablet.class.getName());    // Лог для ведения сключений
    private LinkedBlockingQueue<Order> queue;


    //констуктор класса
    public Tablet(int number) {
        this.number = number;

    }

    // будет создавать заказ из тех блюд, которые выберет пользователь. При ошибке ввода будет
    public Order createOrder(){
        Order order = null; // создание заказа
        try {
            order = new Order(this);
            return order;
            }
            catch (Exception e) {
                logger.log(Level.SEVERE, "Console is unavailable.");
                return null;
            }
        finally {
            processOrder(order);
        }
    }

    private void processOrder(Order order) {
        if (!order.isEmpty()) {
//            setChanged(); // сообщение что произошли изменения
//            notifyObservers(order); // Отправляем обсерверу (Повару) заказ
            try {
                queue.put(order);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
            AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime()*60); // создаем рекламу
            try {
                manager.processVideos();    // показываем рекламу пока готовится заказ
            } catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }
    }

    // Будет создавать ТЕСТОВЫЙ случайный заказ из случайных блюд
    public void createTestOrder(){
        TestOrder testorder = null; // создание заказа

        try {
            testorder= new TestOrder(this);
//            return testorder;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
//            return null;
        }
        finally {
            processOrder(testorder);
        }
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }

    public void setQueue(LinkedBlockingQueue<Order> orderQueue) {
        this.queue = orderQueue;
    }
}
