package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

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
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[1].length; j++) {
                gameField[i][j] = 0;
            }
        }
    }

    private void drawScene(){
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[1].length; j++) {
                setCellColor(j, i, Color.GRAY);
            }
        }
    }
}
