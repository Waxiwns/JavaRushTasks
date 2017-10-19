package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by Max on 02.09.2017.
 */

// С его помощью будем регистрировать события в хранилище.
public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage= new StatisticStorage();
//    private Set<Cook> cooks = new HashSet<Cook>();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {}

    // Будет регистрироваь событие в хранилище
    public void register(EventDataRow data){
        statisticStorage.put(data);
    }
//    public void register(Cook cook){
//        cooks.add(cook);
//    }

    // из хранилища достанет все данные, относящиеся к отображению рекламы, и посчитает общую прибыль за каждый день.
    public Map<Date, Double> getAmountPerDay(){
        List<EventDataRow> list = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        Map<Date, Double> map = new TreeMap<Date, Double>(Collections.reverseOrder());

        for (EventDataRow event :
                list) {
            VideoSelectedEventDataRow videoEvent = (VideoSelectedEventDataRow) event;

            Calendar c = Calendar.getInstance();
            c.setTime(videoEvent.getDate());
            GregorianCalendar gc = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            Date dateEvent = gc.getTime();

            Double amount = 1.0*videoEvent.getAmount()/100;
            if (!map.containsKey(dateEvent)){
                map.put(dateEvent, amount);
            }
            else {
                amount += map.get(dateEvent);
                map.put(dateEvent, amount);
            }
        }
        return map;
    }

    // из хранилища достанет все данные, относящиеся к работе повара, и посчитает общую продолжительность работы для каждого повара отдельно.
    public Map<Date, TreeMap<String, Integer>> getCookingTimeSecondsPerDay(){
        List<EventDataRow> list = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        Map<Date, TreeMap<String, Integer>> map = new TreeMap<Date, TreeMap<String, Integer>>(Collections.reverseOrder());

        for (EventDataRow event :
                list) {
            CookedOrderEventDataRow eventDataRow = (CookedOrderEventDataRow) event;
            Calendar c = Calendar.getInstance();
            c.setTime(eventDataRow.getDate());
            GregorianCalendar gc = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            Date dateEvent = gc.getTime();
            String cookName = eventDataRow.getCookName();
            int cookingTime = (int)Math.ceil(eventDataRow.getCookingTimeSeconds()/60);

            if (!map.containsKey(dateEvent)){
                TreeMap<String, Integer> mapNameTime = new TreeMap<String, Integer>();
                mapNameTime.put(cookName, cookingTime);
                map.put(dateEvent, mapNameTime);
            }
            else {
                Map<String, Integer> mapNameTime = map.get(dateEvent);
                if (!mapNameTime.containsKey(cookName))
                    mapNameTime.put(cookName, cookingTime);
                else
                    mapNameTime.put(cookName, cookingTime + mapNameTime.get(cookName));
            }
        }
        return map;
    }

    // Хранилище событий
    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            storage.put(EventType.COOKED_ORDER, new ArrayList<EventDataRow>());
            storage.put(EventType.SELECTED_VIDEOS, new ArrayList<EventDataRow>());
            storage.put(EventType.NO_AVAILABLE_VIDEO, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data){
            if (data == null)
                return;
            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

//    public Set<Cook> getCooks() {
//        return cooks;
//    }
}
