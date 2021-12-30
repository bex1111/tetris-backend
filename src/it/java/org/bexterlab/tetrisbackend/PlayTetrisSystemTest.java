package org.bexterlab.tetrisbackend;

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
        restHelper.callStartGameWithTestUser(TEST_USER);
        Thread.sleep(10000);
    }
}
