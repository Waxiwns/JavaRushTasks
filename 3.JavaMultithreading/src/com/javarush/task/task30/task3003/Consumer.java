package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by Max on 12.09.2017.
 */
public class Consumer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Consumer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(450);
            while (!Thread.currentThread().isInterrupted()) {
                queue.take();
                System.out.format("Processing " + queue.take().toString() + "\n");
            }
        } catch (InterruptedException e) {
//                e.printStackTrace();
        }
    }
}
