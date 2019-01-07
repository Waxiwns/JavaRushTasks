package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class Apple extends GameObject {
    private static final String APPLE_SIGN = "\uD83C\uDF4E";
    public boolean isAlive = true;
    Color color;

    public void draw(SnakeGame game){
        if (isAlive) {
            // всегда красное яблочко
//            game.setCellValueEx(x, y, Color.NONE, APPLE_SIGN, Color.RED, 75);

            // разноцветное яблочко
            color = Color.values()[game.getRandomNumber(Color.values().length)];
            if (color == game.getCellColor()) color = Color.RED;
            game.setCellValueEx(x, y, Color.NONE, APPLE_SIGN, color, 75);
        }
    }

    public Apple(int x, int y) {
        super(x, y);
    }
}
