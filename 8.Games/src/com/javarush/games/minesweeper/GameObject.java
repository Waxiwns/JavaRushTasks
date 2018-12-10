package com.javarush.games.minesweeper;

public class GameObject {
    public int x;
    public int y;
    public boolean isMine;
    public boolean isOpen;
    public int countMineNeighbors = 0;

    public GameObject(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }

    @Override
    public String toString() {
        return "x - " + this.x + " " + "y - " + this.y;
    }
}
