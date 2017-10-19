package com.javarush.task.task35.task3513;

/**
 * Created by Max on 14.09.2017.
 */

//описывает эффективность хода
public class MoveEfficiency implements Comparable<MoveEfficiency >{
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        if (o == null) return 1;
        if (this.numberOfEmptyTiles != o.numberOfEmptyTiles)
            return Integer.compare(this.numberOfEmptyTiles, o.numberOfEmptyTiles);
        else return Integer.compare(this.score, o.score);
    }
}
