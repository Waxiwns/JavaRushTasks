package com.javarush.games.minesweeper;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private int WIDTH = 25;
    private int HIGHT = 15;
    private static final int COMPLEXITY = 6;
    private int countClosedTiles = WIDTH * HIGHT;
    private int score = 0;
        private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private GameObject[][] gameField = new GameObject[HIGHT][WIDTH];
    private int countMinesOnField = 0;
    private int countFlags;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HIGHT);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (!isGameStopped) openTile(x, y);
        else restart();
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void createGame() {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                setCellValue(j, i, "");
                boolean isMine = false;
                if (getRandomNumber(COMPLEXITY) == 1) {
                    isMine = true;
                    countMinesOnField++;
                }
                gameField[i][j] = new GameObject(j, i, isMine);
                setCellColor(j, i, Color.ORANGE);
            }
        }
        System.out.println(countMinesOnField);
        countFlags = countMinesOnField;
        countMineNeighbors();   // подсчет мин вокруг каждой ячейки
    }

    // список соседних ячеек
    private List<GameObject> getNeighbors(GameObject obj){
        List<GameObject> neighbors = new ArrayList<>();
        int left = (obj.x - 1 < 0) ? 0 : obj.x - 1;
        int right = (obj.x + 1 > WIDTH - 1) ? WIDTH - 1 : obj.x + 1;
        int up = (obj.y - 1 < 0) ? 0 : obj.y - 1;
        int down = (obj.y + 1 > HIGHT - 1) ? HIGHT - 1 : obj.y + 1;
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
                GameObject obj = gameField[i][j];

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
        if (!gameField[y][x].isOpen && !gameField[y][x].isFlag && !isGameStopped){
            gameField[y][x].isOpen = true;
            countClosedTiles--;
            if (!gameField[y][x].isMine){
                score += 5;
                setScore(score);
                if (countClosedTiles == countMinesOnField) win();
            }
            else {
                setCellValueEx(x, y, Color.RED, MINE, Color.BLACK, 75);
                gameOver();
            }

            if (gameField[y][x].countMineNeighbors > 0){
                setCellNumber(x, y, gameField[y][x].countMineNeighbors);
                setCellColor(x, y, Color.GREEN);
                setCellTextSize(x, y, 65);

                Color color = Color.GRAY;
                switch(gameField[y][x].countMineNeighbors){
                    case 1 :
                        color = Color.CRIMSON;
                        break; //необязательно
                    case 2 :
                        color = Color.BLUE;
                        break; //необязательно
                    case 3 :
                        color = Color.BROWN;
                        break; //необязательно
                    case 4 :
                        color = Color.DEEPPINK;
                        break; //необязательно
                    case 5 :
                        color = Color.DARKORANGE;
                        break; //необязательно
                    case 6 :
                        color = Color.DARKSLATEBLUE;
                        break; //необязательно
                    case 7 :
                        color = Color.CRIMSON;
                        break; //необязательно
                    case 8 :
                        color = Color.FUCHSIA;
                        break; //необязательно
                }
                setCellTextColor(x, y, color);
            }

            else if (gameField[y][x].countMineNeighbors == 0 && !gameField[y][x].isMine){
                setCellValue(x, y, "");
                setCellColor(x, y, Color.GREEN);
                List<GameObject> neighs = getNeighbors(gameField[y][x]);
                for (GameObject neigh : neighs) {
                    if (!neigh.isOpen && !neigh.isMine) openTile(neigh.x, neigh.y);
                }
            }
        }
    }

    private void markTile(int x, int y){
        if (!gameField[y][x].isOpen && !isGameStopped){
            if (!gameField[y][x].isFlag){
                if (countFlags > 0){
                    gameField[y][x].isFlag = true;
                    setCellValue(x, y, FLAG);
                    setCellColor(x, y, Color.AQUA);
                    countFlags--;
                }
            }
            else{
                gameField[y][x].isFlag = true;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.ORANGE);
                gameField[y][x].isFlag = false;
                countFlags++;
            }
        }
    }

    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.CADETBLUE, "You WIN! CONGRATS", Color.GOLD, 50);
    }

    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.DARKRED, "GAME OVER", Color.BLACK, 75);
    }

    private void restart(){
        isGameStopped = false;
        countClosedTiles = WIDTH * HIGHT;
        countFlags = 0;
        countMinesOnField = 0;
        score = 0;

        setScore(score);
        createGame();
    }
}
