package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 15;
    private Color cellColor = Color.LIGHTSLATEGRAY;
    private Snake snake;
    private int turnDelay;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        snake.move();
        drawScene();
    }

    private void createGame(){
        turnDelay = 300;

        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        drawScene();
        setTurnTimer(turnDelay);
    }

    private void drawScene(){
        // разукрашиваем все ячейки
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                setCellColor(j, i, cellColor);
            }
        }
        snake.draw(this);
    }
}
