package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 15;
    private Color cellColor = Color.GREENYELLOW;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame(){
        drawScene();
        Apple apple = new Apple(7, 7);
        apple.draw(this);
    }

    private void drawScene(){
        // разукрашиваем все ячейки
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                setCellColor(j, i, cellColor);
            }
        }
    }
}
