package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a)
    {
        int count = 0;
        int h     = a.length;
        int w     = a[0].length;
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                if (a[i][j] == 1)
                {
                    count++;
                    int k;
                    for (k = j + 1; k < w; k++)// поиск ширины
                    {
                        if (a[i][k] == 0)
                            break;
                    }
                    int hW = k;
                    for (k = i + 1; k < h; k++)// поиск высоты
                    {
                        if (a[k][j] == 0)
                            break;
                    }
                    int hC = k;
                    for (int i1 = 0; i1 < hC; i1++) //очистить
                        for (int j1 = 0; j1 < hW; j1++)
                            a[i1][j1] = 0;

                }
            }
        }
        return count;
    }
}
