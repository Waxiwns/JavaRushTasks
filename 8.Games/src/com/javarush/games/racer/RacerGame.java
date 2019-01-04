package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64;     // x - ширина
    public static final int HEIGHT = 64;    // y - высота
    public static final int CENTER_X = WIDTH / 2;    // x - координата разделительной полосы
    private Color centerColor = Color.WHITE;    // цвет разделительной полосы
    public static final int ROADSIDE_WIDTH = 14;    // x - координата обочины
    private Color roadSideColor = Color.GREEN;    // цвет обочины
    private Color roadColor = Color.DIMGRAY;    // цвет дорожного полотна
    private RoadMarking roadMarking;        // дорожная разметка
    private PlayerCar player;        // авто игрока

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < WIDTH && x >= 0 && y < HEIGHT && y >= 0) {
            super.setCellColor(x, y, color);
        }
    }

    //  старт новой игры
    private void createGame(){
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        drawScene();
    }

    // отрисовка всех игровых объектов
    private void drawScene(){
        drawField();
        roadMarking.draw(this);
        player.draw(this);
    }

    // отрисовка фона игрового поля
    private void drawField(){
        for (int i = 0; i < HEIGHT; i++) {              // y
            for (int j = 0; j < WIDTH; j++) {           // x
                if (j == CENTER_X)
                    setCellColor(j, i, centerColor);   // создаем разделиельную полосу
                else if (j >= ROADSIDE_WIDTH && j < WIDTH - ROADSIDE_WIDTH)
                    setCellColor(j, i, roadColor);   // создаем дорожное полотно
                else
                    setCellColor(j, i, roadSideColor);   // создаем обочину
            }
        }
    }
}
