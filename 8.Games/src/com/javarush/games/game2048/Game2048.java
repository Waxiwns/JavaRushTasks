package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Game2048 extends Game {
    private static final int GOAL = 2048;
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
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

    private void createNewNumber() {
        int x;
        int y;

        while (true) {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);

            if (gameField[y][x] == 0) break;
        }

        switch (getRandomNumber(10)) {
            case 9:
                gameField[y][x] = 4;
                break;
            default:
                gameField[y][x] = 2;
        }
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

        // конвертирую в список потому что проще удалять элементы
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < row.length; i++) {
            list.add(row[i]);
        }
        List<Integer> listcopy = new ArrayList<>(list); // копия для сравнения если разные в онце то отдаем true

        // проверка на возможность переместить цифры если да то перемещаем
        int count = list.size();
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) > 0){
                list.add(0, list.get(i));
                list.remove(i + 1);
                i++;
            }
            count--;
            if (count == 0) break;
        }

        // конвертируем назад
        for (int i = 0; i < list.size(); i++) {
            row[i] = list.get(i);
            if (list.get(i) != listcopy.get(i)) truly = true;
        }

        return truly;
    }
}
