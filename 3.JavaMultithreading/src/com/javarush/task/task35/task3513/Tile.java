package com.javarush.task.task35.task3513;

import java.awt.*;

/**
 * Created by Max on 13.09.2017.
 */

// Плитка
public class Tile {
    int value;      // Вес плитки

    // Конструкор класса
    public Tile(int value) {
        this.value = value;
    }
    // Конструкор класса
    public Tile() {
        this.value = 0;
    }

    // возвращает true в случае, если значение поля value равно 0
    public boolean isEmpty(){
        if (value == 0)
            return true;
        return false;
    }

    // возвращающий новый цвет(объект типа Color) (0x776e65) в случае, если вес плитки меньше 16, иначе — 0xf9f6f2.
    public Color getFontColor(){
        if (value < 16)
            return new Color(0x776e65);
        return new Color(0xf9f6f2);
    }

    // возвращающий цвет плитки в зависимости от ее веса в соответствии с нижеприведенными значениями:
    public Color getTileColor(){
        switch (value){
            case 0:
                return new Color(0xcdc1b4);
//                break;
            case 2:
                return new Color(0xeee4da);
//            break;
            case 4:
                return new Color(0xede0c8);
//            break;
            case 8:
                return new Color(0xf2b179);
//            break;
            case 16:
                return new Color(0xf59563);
//            break;
            case 32:
                return new Color(0xf67c5f);
//            break;
            case 64:
                return new Color(0xf65e3b);
//            break;
            case 128:
                return new Color(0xedcf72);
//            break;
            case 256:
                return new Color(0xedcc61);
//            break;
            case 512:
                return new Color(0xedc850);
//            break;
            case 1024:
                return new Color(0xedc53f);
//            break;
            case 2048:
                return new Color(0xedc22e);
//            break;
            default:
                return new Color(0xff0000);
        }
    }

}
