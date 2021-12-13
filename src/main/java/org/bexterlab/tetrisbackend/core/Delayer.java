package org.bexterlab.tetrisbackend.core;

import org.slf4j.Logger;

public class Delayer {

    private final long gameTickTime;
    private final Logger logger;

    public Delayer(long gameTickTime, Logger logger) {
        this.gameTickTime = gameTickTime;
        this.logger = logger;
    }

    public void delay() {
        try {
            Thread.sleep(gameTickTime);
        } catch (InterruptedException e) {
            logger.error("sleepThread: " + e);
            throw new RuntimeException(e);
        }
    }


}
