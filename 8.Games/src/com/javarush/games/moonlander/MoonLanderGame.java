package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;     // x - ширина
    public static final int HEIGHT = 64;    // y - высота
    private static final Color allCellColor = Color.DARKORANGE; // цвет всех ячеек - фон
    private Rocket rocket; // ракета
    private GameObject landscape; // ланшафт
    private GameObject platform; // платформа
    private boolean isUpPressed;        // нажата ли кнопка
    private boolean isLeftPressed;      // нажата ли кнопка
    private boolean isRightPressed;     // нажата ли кнопка
    private boolean isGameStopped;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT); // меняем размер игрового бокса
        createGame();                 // создание игры
    }

    // происходящие действия за каждый ход
    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);      // ракеты движется
        check(); // проверка на пересечение с ландшафтом
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
        isGameStopped = false;
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
        platform = new GameObject(23, HEIGHT - 1, ShapeMatrix.PLATFORM);  // создание платформы
    }

//    проверка на пересечение с ландшафтом
    private void check(){
        if (rocket.isCollision(landscape) && !rocket.isCollision(platform)) gameOver();
        if (rocket.isCollision(platform)) win();
    }

    private void win(){
        isGameStopped = true;
        rocket.land();
        stopTurnTimer();
        showMessageDialog(Color.CADETBLUE, "You WIN! CONGRATS", Color.GOLD, 50);

    }

    private void gameOver(){
        isGameStopped = true;
        rocket.land();
        stopTurnTimer();
        showMessageDialog(Color.DARKRED, "GAME OVER", Color.BLACK, 75);

    }
}
