package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;
import static com.javarush.games.snake.Direction.*;

public class Snake {
    public boolean isAlive = true;
    private Direction direction = LEFT;
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {
        for (int i = 0; i < 3; i++) {
            snakeParts.add(new GameObject((x + i), y));
        }
    }

    public void draw(Game game){
        Color color;

        if (isAlive) color = Color.GREENYELLOW;
        else color = Color.RED;

        for (int i = 0; i < snakeParts.size(); i++) {
            if (i == 0) game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, color, 75);
            else game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, color, 75);
        }
    }

    public void setDirection(Direction direction){
        if (this.direction == UP && direction == DOWN) return;
        if (this.direction == DOWN && direction == UP) return;
        if (this.direction == LEFT && direction == RIGHT) return;
        if (this.direction == RIGHT && direction == LEFT) return;
        this.direction = direction;
    }

    public void move(Apple apple){
        GameObject head = createNewHead();

        if (head.x >= SnakeGame.WIDTH || head.y >= SnakeGame.HEIGHT || head.x < 0 || head.y < 0)
            isAlive = false;
        else {
            snakeParts.add(0, head);
            if (snakeParts.get(0).x == apple.x && snakeParts.get(0).y == apple.y)
                apple.isAlive = false;
            else removeTail();
        }
    }

    public GameObject createNewHead(){
        int x = snakeParts.get(0).x;
        int y = snakeParts.get(0).y;

//        if (direction == UP) y -= 1;
//        if (direction == DOWN) y += 1;
//        if (direction == LEFT) x -= 1;
//        if (direction == RIGHT) x += 1;
        switch (direction){
            case UP:
                y -= 1;
                break;
            case DOWN:
                y += 1;
                break;
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
        }

        if (x >= SnakeGame.WIDTH || y >= SnakeGame.HEIGHT || x < 0 || y < 0)
            isAlive = false;

        return new GameObject(x, y);
    }

    public void removeTail(){
        snakeParts.remove(snakeParts.size() - 1);
    }
}
