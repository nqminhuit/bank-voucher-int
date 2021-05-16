package com.nqminhuit.voucherproviderserver.utils;

import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    public static void safeSleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
