package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by Max on 02.09.2017.
 */

// Событие нет ни одного видео-ролика, который можно показать во время приготовления заказа
public class NoAvailableVideoEventDataRow implements EventDataRow {
    private int totalDuration; // время приготовления заказа в секундах
    private Date currentDate;   // // текущая дата

    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        currentDate = new Date();
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return (int) (new Date().getTime() - currentDate.getTime());
    }
}
