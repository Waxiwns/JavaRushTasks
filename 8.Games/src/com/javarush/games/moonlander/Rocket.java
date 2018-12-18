package com.javarush.games.moonlander;

public class Rocket extends GameObject {
    private double speedY = 0; // скорость движения вниз
    private double speedX = 0; // скорость движения лево право
    private double boost = 0.05;    // ускорение

    public Rocket(double x, double y) {
        super(x, y, ShapeMatrix.ROCKET);
    }

    // движение ракеты
    public void move(boolean isUpPressed, boolean isLeftPressed, boolean isRightPressed){
        // падаем или не падаем
        if (isUpPressed){
            speedY -= boost;
        }
        else{
            speedY += boost;
        }

        y += speedY;

        // если какая либо из клавиш нажата то куда то движемся
        if (isLeftPressed || isRightPressed) {
            if (isLeftPressed){
                speedX -= boost;
            }
            if (isRightPressed){
                speedX += boost;
            }
            x += speedX;
        }
    }
}
