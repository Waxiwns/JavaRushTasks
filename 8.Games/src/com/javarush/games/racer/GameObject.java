package com.javarush.games.racer;

import com.javarush.engine.cell.*;

public class GameObject {
//    координаты верхнего левого угла объекта на игровом поле
    public int x;
    public int y;
//    матрица отображения игрового объекта
    public int[][] matrix;
//    ширина и высота объекта
    public int height;
    public int width;

    public GameObject(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    // отрисовка обьекта в нужном месте игрового поля (координаты прибавляются к координатам игрового поля, цвет объекта берется из цыфры в ячейке матрицы)
    public void draw(Game game) {
        if (matrix != null) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    game.setCellColor(x + j, y + i, Color.values()[matrix[i][j]]);
                }
            }
        }
    }
}
