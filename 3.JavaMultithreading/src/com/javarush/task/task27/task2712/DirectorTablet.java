package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Max on 05.09.2017.
 */

// Планшет директора
public class DirectorTablet {

    // какую сумму заработали на рекламе, сгруппировано по дням
    public void printAdvertisementProfit(){
        Map<Date, Double> map =  StatisticManager.getInstance().getAmountPerDay();
        if (!map.isEmpty()) {
            Double total = 0d;
            for (Map.Entry<Date, Double> pair :
                    map.entrySet()) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String pairDate = format.format(pair.getKey());
                ConsoleHelper.writeMessage(pairDate + " - " + String.format(Locale.ENGLISH, "%.2f", pair.getValue()));
                total += pair.getValue();
            }
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", total));
        }
    }

    // загрузка (рабочее время) повара, сгруппировано по дням
    public void printCookWorkloading(){
        Map<Date, TreeMap<String, Integer>> map = StatisticManager.getInstance().getCookingTimeSecondsPerDay();
        if (!map.isEmpty()){
            for (Map.Entry<Date, TreeMap<String, Integer>> pair :
                    map.entrySet()) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String pairDate = format.format(pair.getKey());
                ConsoleHelper.writeMessage(pairDate);

                for (Map.Entry<String, Integer> pair1 :
                        pair.getValue().entrySet()) {
                    ConsoleHelper.writeMessage(pair1.getKey() + " - " + pair1.getValue() + " min");
                }
                ConsoleHelper.writeMessage("");
            }
        }
    }

    // список активных роликов и оставшееся количество показов по каждому
    public void printActiveVideoSet(){
        List<Advertisement> activeVideo = StatisticAdvertisementManager.getInstance().getActiveViseoSet(true);
        Collections.sort(activeVideo, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        for (Advertisement advertisement :
                activeVideo) {
            ConsoleHelper.writeMessage(advertisement.getName() + " - " + advertisement.getHits());
        }
    }

    // список неактивных роликов (с оставшемся количеством показов равным нулю)
    public void printArchivedVideoSet(){
        List<Advertisement> activeVideo = StatisticAdvertisementManager.getInstance().getActiveViseoSet(false);
        Collections.sort(activeVideo, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

        for (Advertisement advertisement :
                activeVideo) {
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
}
