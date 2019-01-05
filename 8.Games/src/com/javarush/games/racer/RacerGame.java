package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

import static com.javarush.engine.cell.Key.LEFT;
import static com.javarush.engine.cell.Key.RIGHT;

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
    private RoadManager roadManager;
    private boolean isGameStopped;

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

    @Override
    public void onTurn(int step) {
        // если есть пересечения то конец
        if (roadManager.checkCrush(player))
            gameOver();
        else {
            moveAll();
            roadManager.generateNewRoadObjects(this);
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP:
                player.setSpeed(2);
                break;
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                player.setDirection(Direction.RIGHT);
                break;
            case SPACE:
                if (isGameStopped)
                    createGame();
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP:
                player.setSpeed(1);
                break;
            case LEFT:
                if (player.getDirection() == Direction.LEFT)
                    player.setDirection(Direction.NONE);
                break;
            case RIGHT:
                if (player.getDirection() == Direction.RIGHT)
                    player.setDirection(Direction.NONE);
                break;
        }

//        if (key == LEFT && player.getDirection() == Direction.LEFT)
//            player.setDirection(Direction.NONE);
//        else if (key == RIGHT && player.getDirection() == Direction.RIGHT)
//            player.setDirection(Direction.NONE);
    }

    //  старт новой игры
    private void createGame(){
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        isGameStopped = false;
        drawScene();
        setTurnTimer(40);
    }

    // отрисовка всех игровых объектов
    private void drawScene(){
        drawField();
        roadManager.draw(this);
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

    private void moveAll(){
        roadMarking.move(player.speed);
        player.move();
        roadManager.move(player.speed);
    }

    private void gameOver(){
        isGameStopped = true;
        stopTurnTimer();
        player.stop();
        showMessageDialog(Color.DARKOLIVEGREEN, "GAME OVER", Color.BLACK, 75);

    }
}
