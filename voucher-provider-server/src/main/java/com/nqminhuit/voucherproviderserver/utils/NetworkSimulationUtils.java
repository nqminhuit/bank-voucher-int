package com.nqminhuit.voucherproviderserver.utils;

import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkSimulationUtils {

    private static Logger log = LoggerFactory.getLogger(NetworkSimulationUtils.class);

    // private static final List<Integer> NETWORK_TRAFFIC_DELAY_SEC_SIM = List.of(1, 2, 3, 10, 20, 30, 40, 50, 60);
    private static final List<Integer> NETWORK_TRAFFIC_DELAY_SEC_SIM = List.of(1, 2, 3, 10);

    private static int trafficDelayInMillisecond() {
        Random rand = new Random();
        return NETWORK_TRAFFIC_DELAY_SEC_SIM.get(rand.nextInt(NETWORK_TRAFFIC_DELAY_SEC_SIM.size())) * 1000;
    }

    public static void delay() {
        int delay = trafficDelayInMillisecond();
        log.info("network traffic delays for {} milliseconds", delay);
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
