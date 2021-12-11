package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.Delayer;


public class DelayerDummy extends Delayer {
    private final long gameTickTime;


    public DelayerDummy(long gameTickTime) {
        super(gameTickTime, null);
        this.gameTickTime = gameTickTime;
    }

    @Override
    public void delay() {
        try {
            System.out.println("delay " + System.currentTimeMillis());
            Thread.sleep(gameTickTime);
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }
}
