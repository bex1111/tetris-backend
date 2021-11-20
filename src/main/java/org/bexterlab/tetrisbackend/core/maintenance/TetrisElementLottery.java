package org.bexterlab.tetrisbackend.core.maintenance;

import java.security.SecureRandom;

public class TetrisElementLottery {
    private static final SecureRandom random = new SecureRandom();

    public NewElementSpawner.TetrisElement draw() {
        return NewElementSpawner.TetrisElement.values()[
                random.nextInt(
                        NewElementSpawner.TetrisElement.values().length)];
    }

}
