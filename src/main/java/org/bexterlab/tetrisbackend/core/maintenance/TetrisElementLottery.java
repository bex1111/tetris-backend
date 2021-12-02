package org.bexterlab.tetrisbackend.core.maintenance;

import java.security.SecureRandom;

public class TetrisElementLottery {
    private static final SecureRandom random = new SecureRandom();

    public TetrisElement draw() {
        return TetrisElement.values()[
                random.nextInt(
                        TetrisElement.values().length)];
    }

}
