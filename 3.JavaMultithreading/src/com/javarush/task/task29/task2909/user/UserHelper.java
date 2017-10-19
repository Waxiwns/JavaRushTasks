package com.javarush.task.task29.task2909.user;

import java.util.concurrent.atomic.AtomicInteger;

public class UserHelper {
    private User userAnya = new User("Аня", "Смирнова", 10);
    private User userRoma = new User("Рома", "Виноградов", 30);


//    private boolean isManAnya = false;
//    private boolean isManRoma = true;


    public void printUsers() {
        userAnya.printInfo();
        userAnya.printAdditionalInfo();

        userRoma.printInfo();
        userRoma.printAdditionalInfo();
    }

    public int calculateAverageAge() {
//        int age = 28;
        User userUra = new User("Юра", "Карп", 28);

        return (userAnya.getAge() + userRoma.getAge() + userUra.getAge()) / 3;

//        return age;
    }

    public int calculateRate(AtomicInteger base, int age, boolean hasWork, boolean hasHouse) {
        int set = 0;
        set = (base.get() + age / 100);
        set = ((int) (set * (hasWork ? 1.1 : 0.9)));
        set = ((int) (set * (hasHouse ? 1.1 : 0.9)));
        return set;
    }

    public String getBossName(User user) {
//        Work work = user.getWork();
        return user.getBoss();
    }
}