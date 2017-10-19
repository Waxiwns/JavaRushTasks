package com.javarush.task.task27.task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {
        try {
            while (!isValuePresent)
                this.wait();
            }
        catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("Got: " + value);
        isValuePresent = false;
        this.notifyAll();
        return value;
    }

    public synchronized void put(int value) {
        try {
            while (isValuePresent){
                this.wait();
            }
        }catch (InterruptedException e){

        }
        this.value = value;
        isValuePresent = true;
        System.out.println("Put: " + value);
        this.notifyAll();
    }
}
