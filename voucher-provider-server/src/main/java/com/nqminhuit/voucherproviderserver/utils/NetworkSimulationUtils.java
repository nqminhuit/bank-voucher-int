package com.nqminhuit.voucherproviderserver.utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkSimulationUtils {

    private static Logger log = LoggerFactory.getLogger(NetworkSimulationUtils.class);

    private static final Random rand = new Random();

    private static int trafficDelayInMillisecond() {
        return (rand.nextInt(118) + 3) * 1000;
    }

    public static void delay() {
        int delay = trafficDelayInMillisecond();
        log.info("network traffic delays for {} milliseconds", delay);
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
