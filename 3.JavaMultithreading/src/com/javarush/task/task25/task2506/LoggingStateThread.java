package com.javarush.task.task25.task2506;

/**
 * Created by Max on 28.03.2017.
 */
public class LoggingStateThread extends Thread {
    private Thread t;
    private Thread.State state;

    LoggingStateThread(Thread t) {
        this.t = t;
        this.state = t.getState();
//        setDaemon(true);

    }

    @Override
    public void run() {

        System.out.println(state);
        while (!t.isInterrupted()) {
            if (state != t.getState()) {
                state = t.getState();
                System.out.println(t.getState());
            }
            if (state == Thread.State.TERMINATED) {
                interrupt();
                break;
            }
        }

    }
}
