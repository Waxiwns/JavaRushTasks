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
    private Color color = Color.GREENYELLOW;
    private int step = 0;

    public Snake(int x, int y) {
        for (int i = 0; i < 3; i++) {
            snakeParts.add(new GameObject((x + i), y));
        }
    }

    public void draw(Game game){
        int textSize = 75;

        if (!isAlive){
            color = Color.RED;
            textSize /= 1.5;
        }

        for (int i = 0; i < snakeParts.size(); i++) {
            // голова
            if (i == 0) game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, HEAD_SIGN, color, textSize);
            // хвост
            else if (i == snakeParts.size() - 1)
                game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, color, textSize / 2);
            // ячейки тела постоянно меняют размер - эффект анимации
            else if (step % 2 == 0) {
                if (i % 2 == 0)
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, color, textSize);
                else game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, color, textSize - 25);
            }
            else {
                if (i % 2 == 0)
                    game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, color, textSize - 20);
                else game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, Color.NONE, BODY_SIGN, color, textSize - 10);
            }
        }
        step++;
    }

    public void setDirection(Direction direction){
        if (this.direction == UP && direction == DOWN) return;
        if (this.direction == DOWN && direction == UP) return;
        if (this.direction == LEFT && direction == RIGHT) return;
        if (this.direction == RIGHT && direction == LEFT) return;
        if (this.direction == LEFT && snakeParts.get(0).x == snakeParts.get(1).x) return;
        if (this.direction == RIGHT && snakeParts.get(0).x == snakeParts.get(1).x) return;
        if (this.direction == UP && snakeParts.get(0).y == snakeParts.get(1).y) return;
        if (this.direction == DOWN && snakeParts.get(0).y == snakeParts.get(1).y) return;
        this.direction = direction;
    }

    public void move(Apple apple){
        GameObject head = createNewHead();

        moveWithoutBoarder(head);

        if (isAlive){
            snakeParts.add(0, head);

            // если съел яблоко
            if (snakeParts.get(0).x == apple.x && snakeParts.get(0).y == apple.y) {
                apple.isAlive = false;
                color = apple.color;
            }
            else removeTail();
        }
    }

    // движение за пределы поля разрешено
    public void moveWithoutBoarder(GameObject head){
        if (checkCollision(head))
            isAlive = false;
        else if (head.x == SnakeGame.WIDTH)
            head.x = 0;
        else if (head.x < 0)
            head.x = SnakeGame.WIDTH - 1;
        else if (head.y == SnakeGame.HEIGHT)
            head.y = 0;
        else if (head.y < 0)
            head.y = SnakeGame.HEIGHT - 1;
    }

    // движение за пределы поля запрещено
    public void moveWithBoarder(GameObject head){
        if (head.x >= SnakeGame.WIDTH | head.y >= SnakeGame.HEIGHT | head.x < 0 | head.y < 0 | checkCollision(head))
            isAlive = false;
    }

    public GameObject createNewHead(){
        int x = snakeParts.get(0).x;
        int y = snakeParts.get(0).y;

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
        return new GameObject(x, y);
    }

    public void removeTail(){
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject){
        for (GameObject snakePart: snakeParts) {
            if (gameObject.x == snakePart.x && gameObject.y == snakePart.y)
                return true;
            }
        return false;
    }

    public int getLength(){
        return snakeParts.size();
    }
}
