package com.mufeng.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by mufeng on 2018/1/15.
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}