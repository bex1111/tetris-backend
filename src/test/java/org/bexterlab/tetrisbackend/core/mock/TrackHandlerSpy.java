package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.TrackHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class TrackHandlerSpy extends TrackHandler {

    public AtomicInteger maintenanceCallCount = new AtomicInteger(0);

    public TrackHandlerSpy() {
        super(null, null,
                null, null);
    }

    @Override
    public void maintenanceTracks() {
        System.out.println(maintenanceCallCount.incrementAndGet());
    }
}
