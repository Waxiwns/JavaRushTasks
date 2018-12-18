package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;     // x - ширина
    public static final int HEIGHT = 64;    // y - высота
    private static final Color allCellColor = Color.DARKORANGE; // цвет всех ячеек - фон

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
        for (int i = 0; i < HEIGHT; i++) {              // x
            for (int j = 0; j < WIDTH; j++) {           // y
                setCellColor(j, i, allCellColor);   // закрашиваем все ячейки в нужный цвет
            }
        }
    }
}
