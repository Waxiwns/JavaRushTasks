package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;     // x - ширина
    public static final int HEIGHT = 64;    // y - высота

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT); // меняем размер игрового бокса
        createGame();                 // создание игры
    }

    // создание игры
    private void createGame(){
        drawScene();    // отрисовка
    }

    // отрисовка игры
    private void drawScene(){

    }
}
