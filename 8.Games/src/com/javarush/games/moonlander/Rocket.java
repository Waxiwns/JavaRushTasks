package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class Rocket extends GameObject {
    private double speedY = 0; // скорость движения вниз
    private double speedX = 0; // скорость движения лево право
    private double boost = 0.05;    // ускорение
    private double slowdown = boost / 10;    // плавное торможения после отпускания клавиши

    public Rocket(double x, double y) {
        super(x, y, ShapeMatrix.ROCKET);
    }

    // движение ракеты
    public void move(boolean isUpPressed, boolean isLeftPressed, boolean isRightPressed) {
        // падаем или не падаем
        if (isUpPressed) {
            speedY -= boost;
        } else {
            speedY += boost;
        }
        y += speedY;

        // если какая либо из клавиш нажата то куда то движемся
        if (isLeftPressed) {
            speedX -= boost;
            x += speedX;
        } else if (isRightPressed) {
            speedX += boost;
            x += speedX;
        } else if (speedX > slowdown) {         // плавное торможение
            speedX -= slowdown;
        } else if (speedX < -slowdown) {
            speedX += slowdown;
        } else {
            speedX = 0;
        }
        x += speedX;
        checkBorders();
    }

    // ограничение движения за пределы
    private void checkBorders() {
        if (x < 0) {
            x = 0;
            speedX = 0;
        } else if (x + width > MoonLanderGame.WIDTH) {
            x = MoonLanderGame.WIDTH - width;
            speedX = 0;
        }
        if (y <= 0) {
            y = 0;
            speedY = 0;
        }
    }

    // остановка если скорось больше чем надо при сталкновении с платформой
    public boolean isStopped() {
        return speedY < 10 * boost;
    }

    public boolean isCollision(GameObject object) {
        int transparent = Color.NONE.ordinal();

        for (int matrixX = 0; matrixX < width; matrixX++) {
            for (int matrixY = 0; matrixY < height; matrixY++) {
                int objectX = matrixX + (int) x - (int) object.x;
                int objectY = matrixY + (int) y - (int) object.y;

                if (objectX < 0 || objectX >= object.width || objectY < 0 || objectY >= object.height)
                    continue;

                if (matrix[matrixY][matrixX] != transparent && object.matrix[objectY][objectX] != transparent)
                    return true;
            }
        }
        return false;
    }

    public void land(){
        y --;
    }
}
