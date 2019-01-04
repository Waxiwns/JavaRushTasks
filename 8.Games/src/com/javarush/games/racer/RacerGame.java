package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64;     // x - ширина
    public static final int HEIGHT = 64;    // y - высота
    public static final int CENTER_X = WIDTH / 2;    // x - координата разделительной полосы
    public static final int ROADSIDE_WIDTH = 4;    // x - координата обочины

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    //  старт новой игры
    private void createGame(){
        drawScene();
    }

    // отрисовка всех игровых объектов
    private void drawScene(){
        drawField();
    }

    // отрисовка фона игрового поля
    private void drawField(){

    }
}
