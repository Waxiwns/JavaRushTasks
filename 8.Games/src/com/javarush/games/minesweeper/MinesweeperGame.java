package com.javarush.games.minesweeper;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField = 0;
    private int countFlags;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        openTile(x, y);
    }

    private void createGame() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                boolean isMine = false;
                if (getRandomNumber(SIDE + 1) == 1) {
                    isMine = true;
                    countMinesOnField++;
                }

                gameField[j][i] = new GameObject(i, j, isMine);
                setCellColor(i, j, Color.ORANGE);
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();   // подсчет мин вокруг каждой ячейки
    }

    // список соседних ячеек
    private List<GameObject> getNeighbors(GameObject obj){
        List<GameObject> neighbors = new ArrayList<GameObject>();
        int left = (obj.x - 1 < 0) ? 0 : obj.x - 1;
        int right = (obj.x + 1 > SIDE - 1) ? SIDE - 1 : obj.x + 1;
        int up = (obj.y - 1 < 0) ? 0 : obj.y - 1;
        int down = (obj.y + 1 > SIDE - 1) ? SIDE - 1 : obj.y + 1;
        for (int i = left; i <= right; i++) {
            for (int j = up; j <= down; j++) {
                if (i == obj.x && j == obj.y){}
                else neighbors.add(gameField[j][i]);
            }
        }
        return neighbors;
    }

    // подсчет мин вокруг каждой ячейки
    private void countMineNeighbors() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                GameObject obj = gameField[j][i];

                if (!obj.isMine){
                    List<GameObject> neighs = getNeighbors(obj);

                    for (GameObject neigh : neighs) {
                        if (neigh.isMine) obj.countMineNeighbors++;
                    }
                }
            }
        }
    }

    private void openTile(int x, int y){
        if (gameField[y][x].isMine){
            setCellValue(x, y, MINE);
        }
        else if (gameField[y][x].countMineNeighbors > 0){
            setCellNumber(x, y, gameField[y][x].countMineNeighbors);
        }

        gameField[y][x].isOpen = true;
        setCellColor(x, y, Color.GREEN);

        if (gameField[y][x].countMineNeighbors == 0 && !gameField[y][x].isMine){
            setCellValue(x, y, "");
            List<GameObject> neighs = getNeighbors(gameField[y][x]);
            for (GameObject neigh : neighs) {
                if (!neigh.isOpen && !neigh.isMine) openTile(neigh.x, neigh.y);
            }
        }
    }
}
