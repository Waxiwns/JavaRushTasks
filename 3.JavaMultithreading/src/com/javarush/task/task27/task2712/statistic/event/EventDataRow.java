package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by Max on 02.09.2017.
 */

// На данный момент он является интерфейсом-маркером,
// т.к. не содержит методов, и по нему мы определяем, является ли переданный объект событием или нет
public interface EventDataRow {
    public EventType getType();

    //  реализация метода вернет дату создания записи
    public Date getDate();

    //  реализация вернет время - продолжительность
    public int getTime();
}
