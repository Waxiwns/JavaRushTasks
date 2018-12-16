package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Game2048 extends Game {
    private static final int GOAL = 2048;
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (!canUserMove()) {
            gameOver();
            return;
        }

        switch (key) {
            case UP:
                moveUp();
                drawScene();
                break;
            case DOWN:
                moveDown();
                drawScene();
                break;
            case LEFT:
                moveLeft();
                drawScene();
                break;
            case RIGHT:
                moveRight();
                drawScene();
                break;
        }
    }

    private void createGame(){
//        gameField[0][0] = 0;
//        gameField[0][1] = 2;
//        gameField[0][2] = 4;
//        gameField[0][3] = 8;
//        gameField[1][0] = 16;
//        gameField[1][1] = 32;
//        gameField[1][2] = 64;
//        gameField[1][3] = 128;
//        gameField[2][0] = 256;
//        gameField[2][1] = 512;
//        gameField[2][2] = 1024;
//        gameField[2][3] = 2048;

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[1].length; j++) {
                gameField[i][j] = 0;
            }
        }
        createNewNumber();
        createNewNumber();
    }

    private void drawScene(){
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[1].length; j++) {
                setCellColoredNumber(j, i, gameField[i][j]);
            }
        }
    }

    // новая плитка
    private void createNewNumber() {
        if (getMaxTileValue() == GOAL) win(); // если есть ячейка  == 2048 то вызываем метод win

        int x;
        int y;

        while (true) {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);

            if (gameField[y][x] == 0) break;
        }

        if (getRandomNumber(10) > 9)
            gameField[y][x] = 4;
        else
            gameField[y][x] = 2;
    }

    private Color getColorByValue(int i){
        Color color;

        switch (i){
            case 0:
                color = Color.LIGHTGREY;
                break;
            case 2:
                color = Color.LIGHTPINK;
                break;
            case 4:
                color = Color.VIOLET;
                break;
            case 8:
                color = Color.DEEPSKYBLUE;
                break;
            case 16:
                color = Color.LIGHTBLUE;
                break;
            case 32:
                color = Color.LIMEGREEN;
                break;
            case 64:
                color = Color.LAWNGREEN;
                break;
            case 128:
                color = Color.ORANGERED;
                break;
            case 256:
                color = Color.DEEPPINK;
                break;
            case 512:
                color = Color.DARKORANGE;
                break;
            case 1024:
                color = Color.MEDIUMVIOLETRED;
                break;
            case 2048:
                color = Color.DARKVIOLET;
                break;
                default:
                    color = Color.WHITE;
        }
        return color;
    }

    private void setCellColoredNumber(int x, int y, int value){
        if (value != 0) setCellValueEx(x, y, getColorByValue(value), String.valueOf(value));
        else setCellValueEx(x, y, getColorByValue(value), "");
    }

    private boolean compressRow(int[] row){
        boolean truly = false;
        int changes = 1;

        while (changes > 0){
            changes = 0;

            for (int i = 0; i < row.length - 1; i++) {
                if (row[i] == 0 && row[i + 1] > 0){
                    row[i] = row[i + 1];
                    row[i + 1] = 0;
                    truly = true;
                    changes++;
                }
            }
        }

        return truly;
    }
    private boolean mergeRow(int[] row){
        boolean truly = false;

            for (int i = 0; i < row.length - 1; i++) {
                if (row[i] > 0 && row[i] == row[i + 1]){
                    row[i] += row[i + 1];
                    row[i + 1] = 0;
                    truly = true;
                }
            }

        return truly;
    }

    private void rotateClockwise(){
        int tmp;

        for (int i = 0; i < SIDE / 2; i++) {
            for (int j = i; j < SIDE - 1 - i; j++) {
                tmp = gameField[i][j];
                gameField[i][j] = gameField[SIDE - 1 - j][i];
                gameField[SIDE - 1 - j][i] = gameField[SIDE - 1 - i][SIDE - 1 - j];
                gameField[SIDE - 1 - i][SIDE - 1 - j] = gameField[j][SIDE - 1 - i];
                gameField[j][SIDE - 1 - i] = tmp;
            }
        }
    }

    private void moveLeft(){
        int counter = 0;

        for (int i = 0; i < gameField.length; i++) {
            int[] row = gameField[i];

            if (compressRow(row) | mergeRow(row) |  compressRow(row)){
                gameField[i] = row;
                counter++;
            }
        }

        if (counter > 0) createNewNumber();
    }
    private void moveRight(){
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }
    private void moveUp(){
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }
    private void moveDown(){
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private int getMaxTileValue(){
        int max = 0;

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j] > max) max = gameField[i][j];
            }
        }
        return max;
    }

    private void win(){
        showMessageDialog(Color.NONE, "You Win! Congrats", Color.LAWNGREEN, 60);
        isGameStopped = true;
    }

    private void gameOver(){
        showMessageDialog(Color.NONE, "You Lose!", Color.OLDLACE, 60);
        isGameStopped = true;
    }

    private boolean canUserMove(){
        // если есть пустые поля
        for (int y = 0; y < gameField.length; y++) {
            for (int x = 0; x < gameField[y].length; x++) {
                if (gameField[y][x] == 0) return true;
            }
        }

        // если есть одинаковые поля по горизонтали
        for (int y = 0; y < gameField.length; y++) {
            for (int x = 0; x < gameField[y].length - 1; x++) {
                if (gameField[y][x] == gameField[y][x + 1]) return true;
            }
        }

        // если есть одинаковые поля по вертикали
        for (int x = 0; x < gameField.length; x++) {
            for (int y = 0; y < gameField[x].length - 1; y++) {
                if (gameField[y][x] == gameField[y + 1][x]) return true;
            }
        }

        return false;
    }
}
