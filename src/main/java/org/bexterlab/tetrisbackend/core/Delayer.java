package org.bexterlab.tetrisbackend.core;

import org.slf4j.Logger;

public class Delayer {

    private final long gameTickTime;
    private final Logger logger;
    private long lastRunTime;

    public Delayer(long gameTickTime, Logger logger) {
        this.gameTickTime = gameTickTime;
        this.logger = logger;
    }

    public void delay() {
        try {
            //FIXME implement me
            final long currentDelay = getCurrentDelay();
            logger.info("Sleep: " + currentDelay);
            Thread.sleep(gameTickTime);
        } catch (InterruptedException e) {
            logger.error("sleepThread: " + e);
            throw new RuntimeException(e);
        }
    }

    private long getCurrentDelay() {
        final long now = System.currentTimeMillis();
        long differenceToGameTick = now - (lastRunTime + gameTickTime);
        logger.info("now " + now);
        logger.info("lastRunTime " + lastRunTime);
        logger.info("differenceToGameTick " + differenceToGameTick);
        lastRunTime = System.currentTimeMillis();
        if (differenceToGameTick < 0) {
            return 0L;
        } else {
            return differenceToGameTick;
        }
    }
}
