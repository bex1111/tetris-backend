package org.bexterlab.tetrisbackend.entity;

import org.bexterlab.tetrisbackend.configuration.GameConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameConfigurationTest {

    @Test
    void invalidConfigurationTest() {
        Assertions.assertThrows(NullPointerException.class, () -> new GameConfiguration().setMaxUserCount(null));
        Assertions.assertThrows(NullPointerException.class, () -> new GameConfiguration().setDeadRowIndex(null));
        Assertions.assertThrows(NullPointerException.class, () -> new GameConfiguration().setGameTickTime(null));
        Assertions.assertThrows(AssertionError.class, () -> new GameConfiguration().setTrackHeight(null));
        Assertions.assertThrows(AssertionError.class, () -> new GameConfiguration().setTrackHeight(9));
        Assertions.assertThrows(AssertionError.class, () -> new GameConfiguration().setTrackWidth(null));
        Assertions.assertThrows(AssertionError.class, () -> new GameConfiguration().setTrackWidth(5));
    }

    @Test
    void validConfigurationTest() {
        GameConfiguration gameConfiguration = new GameConfiguration()
                .setGameTickTime(1L)
                .setMaxUserCount(2L)
                .setDeadRowIndex(3)
                .setTrackHeight(10)
                .setTrackWidth(11);

        Assertions.assertEquals(1L, gameConfiguration.getGameTickTime());
        Assertions.assertEquals(2L, gameConfiguration.getMaxUserCount());
        Assertions.assertEquals(3, gameConfiguration.getDeadRowIndex());
        Assertions.assertEquals(10, gameConfiguration.getTrackHeight());
        Assertions.assertEquals(11, gameConfiguration.getTrackWidth());
    }
}