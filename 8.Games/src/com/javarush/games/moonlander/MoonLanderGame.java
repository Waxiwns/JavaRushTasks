package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;     // x - ширина
    public static final int HEIGHT = 64;    // y - высота
    private static final Color allCellColor = Color.DARKORANGE; // цвет всех ячеек - фон
    private Rocket rocket; // ракета
    private GameObject landscape; // ланшафт
    private boolean isUpPressed;        // нажата ли кнопка
    private boolean isLeftPressed;      // нажата ли кнопка
    private boolean isRightPressed;     // нажата ли кнопка

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT); // меняем размер игрового бокса
        createGame();                 // создание игры
    }

    // происходящие действия за каждый ход
    @Override
    public void onTurn(int step) {
        rocket.move();      // ракеты движется
        drawScene();        // отрисовка

    }

    // переопределяем метод что б не выдавал исключний когда координаты не попадают в игровое поле
    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < WIDTH && x >= 0 && y < HEIGHT && y >= 0) {
            super.setCellColor(x, y, color);
        }
    }

    // если кнопка нажата то переменная true
    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case UP:
                isUpPressed = true;
                break;
            case LEFT:
                isRightPressed = false;
                isLeftPressed = true;
                break;
            case RIGHT:
                isLeftPressed = false;
                isRightPressed = true;
                break;
        }
    }

//    если кнопка отпущена то переменная false
    @Override
    public void onKeyReleased(Key key) {
        switch (key){
            case UP:
                isUpPressed = false;
                break;
            case LEFT:
                isLeftPressed = false;
                break;
            case RIGHT:
                isRightPressed = false;
                break;
        }
    }

    // создание игры
    private void createGame(){
        isUpPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
        createGameObjects();    // создание ланшафта и ракеты
        drawScene();    // отрисовка
        setTurnTimer(50); // установка скорости игры
    }

    // отрисовка игры
    private void drawScene(){
        for (int i = 0; i < HEIGHT; i++) {              // y
            for (int j = 0; j < WIDTH; j++) {           // x
                setCellColor(j, i, allCellColor);   // закрашиваем все ячейки в нужный фоновый цвет
            }
        }
        landscape.draw(this); // отрисовка ланшафта
        rocket.draw(this); // отрисовка ракеты
    }

    private void createGameObjects(){
        rocket = new Rocket(WIDTH / 2, 0); // создание ракеты
        landscape = new GameObject(
                WIDTH - ShapeMatrix.LANDSCAPE[0].length,
                HEIGHT - ShapeMatrix.LANDSCAPE.length,
                ShapeMatrix.LANDSCAPE);                         // создание ланшафта в нужных координатах
    }
}
