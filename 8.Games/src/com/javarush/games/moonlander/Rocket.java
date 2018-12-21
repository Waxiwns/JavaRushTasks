package com.javarush.games.moonlander;

public class Rocket extends GameObject {
    private double speedY = 0.0; // скорость движения вниз
    private double speedX = 0.0; // скорость движения лево право
    private double boost = 0.05;    // ускорение
    private double slowdown = boost / 10;    // плавное торможения после отпускания клавиши

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

        // плавное торможение
        if (!isLeftPressed && !isRightPressed){
            if (speedX >= -slowdown && speedX <= slowdown)
                speedX = 0;
            if (speedX > slowdown)
                speedX -= slowdown;
            if (speedX < -slowdown)
                speedX += slowdown;
            x += speedX;
        }
        checkBorders();
    }

    // ограничение движения за пределы
    private void checkBorders(){
        if (x < 0){
            x = 0;
            speedX = 0;
        }
        if (x + width > MoonLanderGame.WIDTH) {
            x = MoonLanderGame.WIDTH - width;
            speedX = 0;
        }
        if (y < 0){
            y = 0;
            speedY = 0;
        }
    }

    public boolean isStopped(){
        if (speedY < 10 * boost)
            return true;
        else
            return false;
    }
}
