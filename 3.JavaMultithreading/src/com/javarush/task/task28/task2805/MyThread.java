package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Max on 12.08.2017.
 */
public class MyThread extends Thread {
    static public AtomicInteger count = new AtomicInteger(0);
    static {
        Thread.currentThread().setPriority(count.incrementAndGet());
    }

    public MyThread() {
        Thread.currentThread().setPriority(count.incrementAndGet());
        if (count.get() == 10)
            count.set(0);
    }

    public MyThread(Runnable target) {
        super(target);

        Thread.currentThread().setPriority(count.incrementAndGet());
        if (count.get() == 10)
            count.set(0);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        Thread.currentThread().setPriority(count.incrementAndGet());
        if (count.get() == 10)
            count.set(0);


//        if (group.getMaxPriority() <= count.get())
//            Thread.currentThread().setPriority(group.getMaxPriority());
//        else
//            Thread.currentThread().setPriority(count.incrementAndGet());
    }

    public MyThread(String name) {
        super(name);
        Thread.currentThread().setPriority(count.incrementAndGet());
        if (count.get() == 10)
            count.set(0);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        Thread.currentThread().setPriority(count.incrementAndGet());
        if (count.get() == 10)
            count.set(0);
//        else if (count.get() < 10){
//            if (count.get() > group.getMaxPriority())
//                Thread.currentThread().setPriority(group.getMaxPriority());
////            if (group.getMaxPriority() <= count.get())
////                Thread.currentThread().setPriority(group.getMaxPriority());
//            else
//                Thread.currentThread().setPriority(count.incrementAndGet());
//            }

    }

    public MyThread(Runnable target, String name) {
        super(target, name);

        Thread.currentThread().setPriority(count.incrementAndGet());
        if (count.get() == 10)
            count.set(0);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        Thread.currentThread().setPriority(count.incrementAndGet());
        if (count.get() == 10)
            count.set(0);
//
//        if (group.getMaxPriority() <= count.get())
//            Thread.currentThread().setPriority(group.getMaxPriority());
//        else
//            Thread.currentThread().setPriority(count.incrementAndGet());
        }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        Thread.currentThread().setPriority(count.incrementAndGet());
        if (count.get() == 10)
            count.set(0);
//        if (group.getMaxPriority() <= count.get())
//            Thread.currentThread().setPriority(group.getMaxPriority());
//        else
//            Thread.currentThread().setPriority(count.incrementAndGet());

    }
}
