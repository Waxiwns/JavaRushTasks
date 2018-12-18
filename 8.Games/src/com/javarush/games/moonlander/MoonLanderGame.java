package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;     // x - ширина
    public static final int HEIGHT = 64;    // y - высота
    private static final Color allCellColor = Color.DARKORANGE; // цвет всех ячеек - фон
    private Rocket rocket; // ракета
    private GameObject landscape; // ланшафт

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT); // меняем размер игрового бокса
        createGame();                 // создание игры
    }

    // создание игры
    private void createGame(){
        createGameObjects();
        drawScene();    // отрисовка
    }

    // отрисовка игры
    private void drawScene(){
        for (int i = 0; i < HEIGHT; i++) {              // x
            for (int j = 0; j < WIDTH; j++) {           // y
                setCellColor(j, i, allCellColor);   // закрашиваем все ячейки в нужный фоновый цвет
            }
        }
        landscape.draw(this); // отрисовка ланшафта
        rocket.draw(this); // отрисовка ракеты
    }

    private void createGameObjects(){
        rocket = new Rocket(WIDTH / 2, 0); // создание ракеты
        landscape = new GameObject(
                WIDTH - ShapeMatrix.LANDSCAPE[0].length,
                HEIGHT - ShapeMatrix.LANDSCAPE.length,
                ShapeMatrix.LANDSCAPE);                         // создание ланшафта в нужных координатах
    }
}
