package org.bexterlab.tetrisbackend;

import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.gamer.GamerAppRunner;
import org.bexterlab.tetrisbackend.helper.RestHelper;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.bexterlab.tetrisbackend.TestConstants.TEST_USER;

public class PlayTetrisSystemTest {

    @Test
        //@Disabled
    void name() throws InterruptedException {
        TetrisAppRunner tetrisAppRunner = new TetrisAppRunner();
        GamerAppRunner gamerAppRunner = new GamerAppRunner();
        RestHelper restHelper = new RestHelper();
        String token = restHelper.callStartGameWithTestUser(TEST_USER);
        while (true) {
            restHelper.callControlWithTestUserAndToken(TEST_USER, token,
                    Movement.values()[new Random().nextInt(Movement.values().length)]);
            Thread.sleep(1000);
        }
    }
}
