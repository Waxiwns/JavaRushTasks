//package com.javarush.task.task27.task2712;
//
//import com.javarush.task.task27.task2712.kitchen.Cook;
//import com.javarush.task.task27.task2712.kitchen.Order;
//import com.javarush.task.task27.task2712.statistic.StatisticManager;
//
//import java.util.Observable;
//import java.util.Observer;
//import java.util.Set;
//import java.util.concurrent.LinkedBlockingQueue;
//
///**
// * Created by Max on 08.09.2017.
// */
//public class OrderManager implements Observer {
////    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<Order>();
//
//    public OrderManager() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Set<Cook> cooks = StatisticManager.getInstance().getCooks();
//                while (true) {
//                    try {
//                        Thread.sleep(10);
//                    }
//                    catch (InterruptedException e) {}
//                    if (!orderQueue.isEmpty()) {
//                        for (Cook cook : cooks) {
//                            if (!cook.isBusy()) {
//                                Order order = orderQueue.poll();
//                                if (order != null)
//                                    cook.startCookingOrder(order);
//                            }
//                            if (orderQueue.isEmpty())
//                                break;
//                        }
//                    }
//                }
//            }
//        });
//        thread.isDaemon();
//        thread.start();
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//        try {
//            orderQueue.put((Order) arg);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
