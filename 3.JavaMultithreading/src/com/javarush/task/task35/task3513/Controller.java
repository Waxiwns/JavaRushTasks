package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Max on 13.09.2017.
 */

// будет следить за нажатием клавиш во время игры
public class Controller extends KeyAdapter{
    private Model model;        // модель с игровым полем
    private View view;          // представление
    private static final int WINNING_TILE = 2048;   // вес плитки при достижении которого игра будет считаться выигранной.
    // возвращает игровое поле модели
    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    // возвращает текущий счет
    public int getScore(){
        return model.score;
    }

    // конструктор
    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    // возвращает игровое поле в начальное состояние.
    public void resetGame(){
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
    }

    //

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_ESCAPE == e.getKeyCode())
            resetGame();
        if (!model.canMove())
            view.isGameLost = true;
        if (!view.isGameLost && !view.isGameWon){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT: model.left(); break;
                case KeyEvent.VK_RIGHT: model.right();break;
                case KeyEvent.VK_DOWN: model.down();break;
                case KeyEvent.VK_UP: model.up();break;
                case KeyEvent.VK_Z: model.rollback(); break;
                case KeyEvent.VK_R: model.randomMove(); break;
                case KeyEvent.VK_A: model.autoMove(); break;
            }
        }
        if (model.maxTile == WINNING_TILE)
            view.isGameWon = true;
        view.repaint();
    }

    public View getView() {
        return view;
    }
}
