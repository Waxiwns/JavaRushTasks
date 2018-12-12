package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    private Color cellColor = Color.LIGHTSLATEGRAY;
    private Snake snake;
    private int turnDelay;
    private Apple apple;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
        }
    }

    private void createGame(){
        turnDelay = 300;
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        apple = new Apple(5, 5);

        drawScene();
        setTurnTimer(turnDelay);
    }

    private void drawScene(){
        // разукрашиваем все ячейки
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                setCellValueEx(j, i, cellColor, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }
}
