package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
Создай свой UncaughtExceptionHandler в виде локального класса внутри конструктора.
UncaughtExceptionHandler должен маскировать звездочками имя трэда и выводить в консоль описание возникшей ошибки.
«Thread-0» должно быть заменено на «********».
«Thread-4321» должно быть заменено на «***********».


Требования:
1. Определение класса Solution и его поля менять нельзя.
2. Конструктор Solution должен создавать свой UncaughtExceptionHandler, и сохранять его в поле handler.
3. Созданный UncaughtExceptionHandler должен выводить описание возникшей ошибки в консоль.
4. В описании ошибки имя трэда должно быть замаскировано символами "*".
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {
        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        //init handler here
        this.handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                String s = e.getMessage();
                int i1 = s.indexOf("Thread-");
                int i2 = s.indexOf(" ", i1) - 1;
                String name = "";
                for (int i = 0; i < Thread.currentThread().getName().length(); i++) {
                    name += "*";
                }
                s = s.replace(Thread.currentThread().getName(), name);

                System.out.println(s);


//                String[] s1 = s.split(" ");
//                String s2 = "";
//                for (int i = 0; i < s1.length; i++) {
//                    if (s1[i].contains("Thread-")){
//                        String s3 = "";
//                        for (int j = 0; j < s1[i].length(); j++) {
//                            s3 += "*";
//                        }
//                        s2 += s3 + " ";
//                    }
//                    else {
//                        s2 += s1[i] + " ";
//                    }
//                }
//                s = s2.trim();
//                System.out.println(s);

            }
        };
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
        Thread th = new Thread(new Solution(new TimerTask()
        {
            @Override
            public void run()
            {
                throw new Error();
            }
        }));
        th.start();
    }
}