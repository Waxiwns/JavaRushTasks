package com.javarush.games.moonlander;

public class Rocket extends GameObject {
    private double speedY = 0; // скорость падения
    private double boost = 0.05;    // ускорение

    public Rocket(double x, double y) {
        super(x, y, ShapeMatrix.ROCKET);
    }

    // движение ракеты
    public void move(){
        speedY += boost;        // падение с
        y += speedY;            // ускорением
    }
}
