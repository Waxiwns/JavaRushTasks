package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class GameObject {
    public double x;
    public double y;
    public int height;
    public int width;
    public int[][] matrix;

    public GameObject(double x, double y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    // отрисовка обьекта в нужном месте игрового поля (координаты прибавляются к координатам игрового поля, цвет ракеты берется из цыфры в мячейке матрицы)
    public void draw(Game game) {
        if (matrix != null) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    game.setCellColor((int) (x + j), (int) (y + i), Color.values()[matrix[i][j]]);
                }
            }
        }
    }
}
