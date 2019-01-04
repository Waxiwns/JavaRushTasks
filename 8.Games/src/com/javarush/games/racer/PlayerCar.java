package com.javarush.games.racer;

import com.javarush.games.racer.road.RoadManager;

// авто игрока
public class PlayerCar extends GameObject {
    private static int playerCarHeight = ShapeMatrix.PLAYER.length;
    public int speed = 1;           // скорость движения
    private Direction direction;    // направление движения


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public PlayerCar() {
        super(RacerGame.WIDTH / 2 + 2, RacerGame.HEIGHT - playerCarHeight - 1, ShapeMatrix.PLAYER);
    }

    public void move() {
        // не допускаем выхода за пределы дороги (допускаем выезд только колеса)
        if (x < RoadManager.LEFT_BORDER) x = RoadManager.LEFT_BORDER;
        if (x > RoadManager.RIGHT_BORDER - width) x = RoadManager.RIGHT_BORDER - width;

        if (direction == Direction.LEFT) x--;
        else if (direction == Direction.RIGHT) x++;

    }
}
