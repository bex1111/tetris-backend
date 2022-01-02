package org.bexterlab.tetrisbackend;

import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.gamer.GamerAppRunner;
import org.bexterlab.tetrisbackend.helper.RestHelper;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.TestConstants.TEST_USER;

public class PlayTetrisSystemTest {

    @Test
    void name() throws InterruptedException {
        TetrisAppRunner tetrisAppRunner = new TetrisAppRunner();
        GamerAppRunner gamerAppRunner = new GamerAppRunner();
        RestHelper restHelper = new RestHelper();
        String token = restHelper.callStartGameWithTestUser(TEST_USER);
        restHelper.callControlWithTestUserAndToken(TEST_USER, token, Movement.ROTATE_LEFT);
        restHelper.callControlWithTestUserAndToken(TEST_USER, token, Movement.MOVE_RIGHT);
        Thread.sleep(100000);
    }
}
