package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 28.02.2017.
 */
public class Hippodrome {
    static Hippodrome game;
    private  List<Horse> horses;
    public Hippodrome(List<Horse> list){
        this.horses = list;
    }

    public  List<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 1; i <= 100 ; i++) {
            this.move();
            this.print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void move(){
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).move();
        }
    }
    public void print(){
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
    public Horse getWinner(){
//        Horse win=null;
        double dis = 0.0;
        int num = 0;
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getDistance() > dis){
                dis = horses.get(i).getDistance();
                num = i;
            }
        }
        return getHorses().get(num);
    }

    public void printWinner(){
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).equals(getWinner()))
            System.out.println("Winner is " + getWinner().getName() + "!");
        }
    }

    public static void main(String[] args) {
        ArrayList<Horse> list = new ArrayList();
        list.add(new Horse("Первая", 3, 0));
        list.add(new Horse("Вторая", 3, 0));
        list.add(new Horse("Третья", 3, 0));
        Hippodrome.game = new Hippodrome(list);

        game.run();
        game.printWinner();
    }
}
