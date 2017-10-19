package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 06.09.2017.
 */

// будет предоставлять информацию (список активных и неактивных роликов) из AdvertisementStorage в нужном нам виде
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {

    }

    public List<Advertisement> getActiveViseoSet(boolean active) {
        List<Advertisement> videos = storage.list();
        List<Advertisement> getVideos = new ArrayList<Advertisement>();
        for (Advertisement advertisement :
                    videos) {
            if (active == true) {
                if (advertisement.getHits() > 0) {
                    getVideos.add(advertisement);
                }
            }
            else {
                if (advertisement.getHits() <= 0) {
                    getVideos.add(advertisement);
                }
            }
        }
        return getVideos;
    }
}
