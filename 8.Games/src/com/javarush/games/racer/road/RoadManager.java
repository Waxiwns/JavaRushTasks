package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;      // крайняя левая и крайняя правая позиции координат x матриц
    private static final int FOURTH_LANE_POSITION = 44;     // объектов-препятствий на проезжей части
    private List<RoadObject> items = new ArrayList<>();     // список всех объектов

    private RoadObject createRoadObject(RoadObjectType type, int x, int y){
        if (type == RoadObjectType.THORN) return new Thorn(x, y);

        return null;
    }

    private void addRoadObject(RoadObjectType type, Game game){
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject obj = createRoadObject(type, x, y);
        if (obj != null)
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
            object.move(object.speed + boost);
        }
    }
    private boolean isThornExists(){
        for (RoadObject object :
                items) {
            if (object.type == RoadObjectType.THORN) return true;
        }
        return false;
    }

    private void generateThorn(Game game){
        if (!isThornExists() && game.getRandomNumber(100) < 10){
            addRoadObject(RoadObjectType.THORN, game);
        }
    }

    public void generateNewRoadObjects(Game game){
        generateThorn((game));
    }
}
