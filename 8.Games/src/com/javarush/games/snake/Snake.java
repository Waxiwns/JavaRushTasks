package com.javarush.games.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int x, int y) {
        for (int i = 0; i < 3; i++) {
            snakeParts.add(new GameObject((x + i), y));
        }
    }
}
