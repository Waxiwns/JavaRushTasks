package com.javarush.games.moonlander;

import java.util.List;

public class RocketFire extends GameObject {
    private List<int[][]> frames;          // Список матриц шагов анимаций
    private int frameIndex;      // индекс текущей матрицы-анимации
    private boolean isVisible;   // видимость огня

    public RocketFire(List<int[][]> frameList) {
        super(0,0, frameList.get(0));
        frames = frameList;
    }
}
