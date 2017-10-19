package com.javarush.task.task27.task2712.statistic.event;

import com.javarush.task.task27.task2712.ad.Advertisement;

import java.util.Date;
import java.util.List;

/**
 * Created by Max on 02.09.2017.
 */

// Событие выбрали набор видео-роликов для заказа
public class VideoSelectedEventDataRow implements EventDataRow{
    private List<Advertisement> optimalVideoSet;    // список видео-роликов, отобранных для показа
    private long amount;                            // сумма денег в копейках
    private int totalDuration;                      // общая продолжительность показа отобранных рекламных роликов
    private Date currentDate;                       // текущая дата

    public VideoSelectedEventDataRow(List<Advertisement> optimalVideoSet, long amount, int totalDuration) {
        this.optimalVideoSet = optimalVideoSet;
        this.amount = amount;
        this.totalDuration = totalDuration;
        currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.SELECTED_VIDEOS;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return (int) (new Date().getTime() - currentDate.getTime());
    }

    public long getAmount() {
        return amount;
    }
}
