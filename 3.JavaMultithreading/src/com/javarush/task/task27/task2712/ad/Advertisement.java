package com.javarush.task.task27.task2712.ad;

/**
 * Created by Max on 01.09.2017.
 */

//Рекламное обьявление (класс отвечающий за рекламу)
public class Advertisement {
    private Object content;                     // Видео
    private String name;                        // имя/название
    private long initialAmount;       // начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits;                 // количество оплаченных показов
    private int duration;                       // продолжительность в секундах
    private long amountPerOneDisplaying;        // стоимость одного показа рекламного объявления в копейках (initialAmount/hits)

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount/hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getInitialAmount() {
        return initialAmount;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    //
    public void revalidate(){
        if (hits <= 0){
            throw new NoVideoAvailableException();
        }
        else hits--;

//        if (hits <= 0) throw new NoVideoAvailableException();
//        if (hits == 1) { amountPerOneDisplaying += initialAmount % amountPerOneDisplaying; }
//        hits--;
    }

    public int getHits() {
        return hits;
    }
}
