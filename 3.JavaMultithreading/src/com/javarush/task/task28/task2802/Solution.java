package com.javarush.task.task28.task2802;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
В классе Solution создай публичный статический класс AmigoThreadFactory, реализующий интерфейс ThreadFactory.
1. Реализация интерфейсного метода — создайте и верните трэд, который должен:
1.1. не быть демоном,
1.2. иметь нормальный приоритет,
1.3. имя трэда должно иметь шаблон «GN-pool-A-thread-B«,
где GN — это имя группы,
A — это номер фабрики инкрементируется в пределах класса начиная с 1, используйте AtomicInteger,
B — номер треда инкрементируется в пределах конкретной фабрики начиная с 1, используйте AtomicInteger.
2. Каждая фабрика должна иметь ту группу тредов (ThreadGroup), в которой она была создана.
3. Методы main и emulateThreadFactory не участвуют в тестировании.

Пример вывода:
secondGroup-pool-2-thread-1
firstGroup-pool-1-thread-1
firstGroup-pool-1-thread-3
secondGroup-pool-2-thread-3
firstGroup-pool-1-thread-2
secondGroup-pool-2-thread-2
*/
public class Solution {
    public static class AmigoThreadFactory implements ThreadFactory{
        static AtomicInteger groupCount = new AtomicInteger(0);
        AtomicInteger numG = new AtomicInteger(0);
        AtomicInteger numF = new AtomicInteger(0);

        public AmigoThreadFactory(){
            numG.set(groupCount.incrementAndGet());
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            int i = thread.activeCount();
            thread.setDaemon(false);
            thread.setPriority(Thread.NORM_PRIORITY);
            String name = thread.getThreadGroup().getName() + "-pool-" + numG + "-thread-" + numF.incrementAndGet();
//            numG.set(numG.get() + 1);
//            numG.set(fa);
//            numF.set(numF.get() + 1);
            thread.setName(name);

            return thread;
        }
    }

    public static void main(String[] args) {
        class EmulateThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulateThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulateThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
