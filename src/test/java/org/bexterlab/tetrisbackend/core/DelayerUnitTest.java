package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class DelayerUnitTest {

    public static final int TICK_TIME = 50;

    @Test
    void delayTest() {
        final Delayer delayer = new Delayer(TICK_TIME, new LoggerSpy());
        final long timeout = 100L;
        Assertions.assertTimeout(Duration.ofMillis(timeout * 3), () -> repeatDelay(timeout, delayer));
    }

    private void repeatDelay(long timeout, Delayer delayer) {
        for (int i = 0; i < timeout / TICK_TIME; i++) {
            delayer.delay();
        }

    }
}