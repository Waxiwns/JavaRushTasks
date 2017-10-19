package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by Max on 13.09.2017.
 */

// будет содержать игровую логику и хранить игровое поле. Он будет ответственен за все манипуляции производимые с игровым полем
public class Model {
    private static final int FIELD_WIDTH = 4;   // ширина игрового поля
    private Tile[][] gameTiles;                 // игровое поле
    protected int score = 0;                    // текущий счет
    protected int maxTile = 2;                  // максимальный вес плитки на игровом поле
    private Stack previousStates = new Stack(); // стек для сохранения сосояния поля для отката
    private Stack previousScores = new Stack(); // стек для сохранения колличестка очков
    private boolean isSaveNeeded = true;        // флаг указывающий на надобность сохранения состояния


    // Конструктор инициализирующий игровое поле и заполняющий его пустыми плитками
    public Model() {
        resetGameTiles();
    }

    // будет смотреть какие плитки пустуют и менять вес одной из них
    private void addTile(){
        // выбранной случайным образом, на 2 или 4 (на 9 двоек должна приходиться 1 четверка).
        // Получить случайный объект из списка
        // можешь использовав следующее выражение: (размерСписка * случайноеЧислоОтНуляДоЕдиницы).
        if (getEmptyTiles().size() > 0){
            Tile tile = getEmptyTiles().get((int) (Math.random() * getEmptyTiles().size()));
            tile.value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    // получение свободных плиток
    private ArrayList<Tile> getEmptyTiles(){
        ArrayList<Tile> list = new ArrayList<Tile>();

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty())
                    list.add(gameTiles[i][j]);
            }
        }
        return list;
    }

    // Новая игра
    protected void resetGameTiles(){
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }

        addTile();
        addTile();
    }

    // Сжатие плиток смещая пустые
    private boolean compressTiles(Tile[] tiles){
        boolean isChanged = false;

        for (int i = 0; i < tiles.length - 1; i++) {
            for (int j = 0; j < tiles.length-1; j++) {
                if (tiles[j].isEmpty() && !tiles[j+1].isEmpty()){
                    tiles[j].value = tiles[j].value ^ tiles[j + 1].value;
                    tiles[j + 1].value = tiles[j].value ^ tiles[j + 1].value;
                    tiles[j].value = tiles[j].value ^ tiles[j + 1].value;
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    // Слияние плиток сложением одинаковых
    private boolean mergeTiles(Tile[] tiles){
        boolean isChanged = false;

        for (int i = 0; i < tiles.length-1; i++) {
            if (!tiles[i].isEmpty() && tiles[i].value == tiles[i+1].value){
                tiles[i].value += tiles[i+1].value;
                tiles[i+1].value = 0;
                score += tiles[i].value;
                if (maxTile < tiles[i].value) {
                    maxTile = tiles[i].value;
                }
                compressTiles(tiles);
                isChanged = true;
            }
        }
        return isChanged;
    }

    // движение влево
    protected void left(){
        if (isSaveNeeded) saveState(gameTiles);

        boolean isChanged = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
                isChanged = true;
        }
        if (isChanged)
            addTile();
        isSaveNeeded = true;
    }

    // поворот поля вправо на 90 градусов
    private void rotateToRight(){
        // поворот по часовой на 90 град
        Tile tmp;
        for (int i = 0; i < FIELD_WIDTH / 2; i++) {
            for (int j = i; j < FIELD_WIDTH - 1 - i; j++) {
                tmp = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[FIELD_WIDTH-j-1][i];
                gameTiles[FIELD_WIDTH-j-1][i] = gameTiles[FIELD_WIDTH-i-1][FIELD_WIDTH-j-1];
                gameTiles[FIELD_WIDTH-i-1][FIELD_WIDTH-j-1] = gameTiles[j][FIELD_WIDTH-i-1];
                gameTiles[j][FIELD_WIDTH-i-1] = tmp;
            }
        }

        /*// поворо против часовой на 90 град
        for (int i = 0; i < FIELD_WIDTH / 2; i++) {                // border -> center
            for (int j = i; j < FIELD_WIDTH  - 1 - i ; j++) {    // left -> right
                // меняем местами 4 угла
                Tile tmp = gameTiles[i][j];
                gameTiles[i][j] = gameTiles[j][FIELD_WIDTH - 1 - i];
                gameTiles[j][FIELD_WIDTH - 1 - i] = gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j];
                gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j] = gameTiles[FIELD_WIDTH - 1 - j][i];
                gameTiles[FIELD_WIDTH - 1 - j][i] = tmp;
            }
        }*/
    }

    // движение вверх
    protected void up(){
        saveState(gameTiles);
        rotateToRight();
        rotateToRight();
        rotateToRight();
        left();
        rotateToRight();
    }

    // движение вправо
    protected void right(){
        saveState(gameTiles);
        rotateToRight();
        rotateToRight();
        left();
        rotateToRight();
        rotateToRight();
    }

    // движение вниз
    protected void down(){
        saveState(gameTiles);
        rotateToRight();
        left();
        rotateToRight();
        rotateToRight();
        rotateToRight();
    }

    // возвращает игровое поле
    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    // возвращающий true в случае, если в текущей позиции возможно сделать ход так, чтобы состояние игрового поля изменилось. Иначе — false.
    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) return true;

        // проверяем по горизонтали
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j + 1].value)
                    return true;
            }
        }
        // проверяем по верикали
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                if (gameTiles[i][j].value == gameTiles[i + 1][j].value)
                    return true;
            }
        }
        return false;
    }

    // сохранение предыдущего сосояния для отката
    private void saveState(Tile[][] tiles){
        Tile[][] copy = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                copy[i][j] = new Tile();
                copy[i][j].value = tiles[i][j].value;
            }
        }

        previousStates.push(copy);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    //  восстановление предыдущего состояния (откат)
    public void rollback(){
        if (!previousScores.empty() && !previousStates.empty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }

    // вызывает один из методов движения случайным образом.
    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 1: left(); break;
            case 2: up(); break;
            case 3: right(); break;
            case 4: down(); break;
            default: left(); break;
        }
    }

    // будет возвращать true, в случае, если вес плиток в массиве gameTiles отличается от веса плиток в верхнем массиве стека previousStates
    protected boolean hasBoardChanged(){
//        int velueGame = 0;
//        int velueSteck = 0;
        Tile[][] steck = (Tile[][]) previousStates.peek();

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
//                velueGame += gameTiles[i][j].value;
//                velueSteck += steck[i][j].value;
                if (gameTiles[i][j].value != steck[i][j].value)
                    return true;
            }
        }
//        if (velueGame != velueSteck)
//            return true;
        return false;
    }

    // возвращает объект типа MoveEfficiency описывающий эффективность переданного хода
    protected MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        else
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();
        return moveEfficiency;
    }

    // умный ход
    protected void autoMove(){
        PriorityQueue<MoveEfficiency>  queue= new PriorityQueue<>(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this :: left));
        queue.offer(getMoveEfficiency(this :: up));
        queue.offer(getMoveEfficiency(this :: right));
        queue.offer(getMoveEfficiency(this :: down));
        queue.peek().getMove().move();
    }
}
