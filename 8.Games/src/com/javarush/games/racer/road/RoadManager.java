package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;      // крайняя левая и крайняя правая позиции координат x матриц
    private static final int FOURTH_LANE_POSITION = 44;     // объектов-препятствий на проезжей части
    private List<RoadObject> items = new ArrayList<>();     // список всех объектов
    private static final int PLAYER_CAR_DISTANCE = 12;
    private int passedCarsCount = 0;

    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y){
        if (type == RoadObjectType.THORN)
            return new Thorn(x, y);
        else if (type == RoadObjectType.DRUNK_CAR)
            return new MovingCar(x, y);
        else
            return new Car(type, x, y);
    }

    private void addRoadObject(RoadObjectType type, Game game){
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject obj = createRoadObject(type, x, y);
        if (isRoadSpaceFree(obj))
            items.add(obj);
    }

    public void draw(Game game){
        for (RoadObject object :
                items) {
            object.draw(game);
        }
    }

    public void move(int boost){
        for (RoadObject object :
                items) {
            object.move(object.speed + boost, items);
        }
        deletePassedItems();
    }
    private boolean isThornExists(){
        for (RoadObject object :
                items) {
            if (object.type == RoadObjectType.THORN) return true;
        }
        return false;
    }
    private boolean isMovingCarExists(){
        for (RoadObject object :
                items) {
            if (object.type == RoadObjectType.DRUNK_CAR) return true;
        }
        return false;
    }

    private void generateThorn(Game game){
        if (!isThornExists() && game.getRandomNumber(100) < 10){
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    private void generateMovingCar(Game game){
        if (!isMovingCarExists() && game.getRandomNumber(100) < 10){
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }

    private void generateRegularCar(Game game){
        if (game.getRandomNumber(100) < 30){
            int carTypeNumber = game.getRandomNumber(4);
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    public void generateNewRoadObjects(Game game){
        generateThorn((game));
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems(){
        List<RoadObject> copyItems = new ArrayList<>(items);
        for (RoadObject object :
                copyItems) {
            if (object.y >= RacerGame.HEIGHT) {
                items.remove(object);
                if (object.type != RoadObjectType.THORN) passedCarsCount++;
            }
        }
    }

    public boolean checkCrush(PlayerCar playerCar){
        for (RoadObject object :
                items) {
            if (object.isCollision(playerCar))
                return true;
        }
        return false;
    }

    private boolean isRoadSpaceFree(RoadObject roadObject){
        for (RoadObject object :
                items) {
            if (object.isCollisionWithDistance(roadObject, PLAYER_CAR_DISTANCE))
                return false;
        }

        return true;
    }
}
