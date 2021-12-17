package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.GameIntercator;

import java.util.concurrent.atomic.AtomicInteger;

public class GameIntercatorSpy extends GameIntercator {

    public AtomicInteger maintenanceCallCount = new AtomicInteger(0);

    public GameIntercatorSpy() {
        super(null, null,
                null, null);
    }

    @Override
    public void maintenanceTracks() {
        System.out.println(maintenanceCallCount.incrementAndGet());
    }
}
