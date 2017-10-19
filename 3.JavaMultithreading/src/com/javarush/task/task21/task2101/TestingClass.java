package com.javarush.task.task21.task2101;

/**
 * Created by Max on 05.08.2017.
 */
public class TestingClass {
    public static void main(String[] args) {
        int mas[][] = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                mas[i][j] = i+j;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                System.out.print(mas[i][j] );
            }
            System.out.println("");
        }
        System.out.println("");
//        rotateToRight(mas);

        int[][] clone = mas.clone();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                System.out.print(clone[i][j] );
            }
            System.out.println("");
        }

    }


    static void rotateToRight(int[][] mas){
//        Tile tmp;
//        for (int i = 0; i < FIELD_WIDTH / 2; i++) {
//            for (int j = i; j < FIELD_WIDTH - 1 - i; j++) {
//                tmp = gameTiles[i][j];
//                gameTiles[i][j] = gameTiles[FIELD_WIDTH-j-1][i];
//                gameTiles[FIELD_WIDTH-j-1][i] = gameTiles[FIELD_WIDTH-i-1][FIELD_WIDTH-j-1];
//                gameTiles[FIELD_WIDTH-i-1][FIELD_WIDTH-j-1] = gameTiles[j][FIELD_WIDTH-i-1];
//                gameTiles[j][FIELD_WIDTH-i-1] = tmp;
//            }
//        }
        for (int i = 0; i < 4/ 2; i++) {                // border -> center
            for (int j = i; j < 4 - 1 - i ; j++) {    // left -> right
                // меняем местами 4 угла
                int tmp = mas[i][j];
                mas[i][j] = mas[j][4 - 1 - i];
                mas[j][4 - 1 - i] = mas[4 - 1 - i][4 - 1 - j];
                mas[4 - 1 - i][4 - 1 - j] = mas[4 - 1 - j][i];
                mas[4 - 1 - j][i] = tmp;
            }
        }
    }

    public static String getName(int i)
    {
        switch(i)
        {
            case 1:
            case 2:
                return "один или два";
            case 3:
            case 4:
            case 5:
                return "от трех до пяти";
            default:
                return "много";
        }
    }


}




/*
        Collections.sort(storage.list(), new Comparator<Advertisement>() {
@Override
public int compare(Advertisement o1, Advertisement o2) {
        int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
        if (result != 0)
        return -result;

        long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
        long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

        return Long.compare(oneSecondCost1, oneSecondCost2);
        }
        });

        // Составление отчета стаистики
        long amount = 0;                            // сумма денег в копейках
        int totalDuration = 0;                      // общая продолжительность показа отобранных рекламных роликов

//                    for (Advertisement ad :
//                            storage.list()) {
//                        amount += ad.getAmountPerOneDisplaying();
//                        totalDuration +=ad.getDuration();
//                    }
//                    StatisticManager.getInstance().register(new VideoSelectedEventDataRow(storage.list(), amount, totalDuration));


        int timeLeft = timeSeconds;
        for (Advertisement advertisement : storage.list()) {

        if (timeLeft < advertisement.getDuration()) {
        continue;
        }

        ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
        + advertisement.getAmountPerOneDisplaying() + ", "
        + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

        amount += advertisement.getAmountPerOneDisplaying();
        totalDuration += advertisement.getDuration();

        timeLeft -= advertisement.getDuration();
        advertisement.revalidate();
        }

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(storage.list(), amount, totalDuration));

        if (timeLeft == timeSeconds) {
        StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
        throw new NoVideoAvailableException();
        }
        }*/