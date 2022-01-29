package org.bexterlab.tetrisbackend;

import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.gamer.GamerAppRunner;
import org.bexterlab.tetrisbackend.helper.RestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientResponseException;

import java.util.Random;

import static org.bexterlab.tetrisbackend.TestConstants.TEST_USER;

public class PlayTetrisSystemTest {

    RestHelper restHelper;
    TetrisAppRunner tetrisAppRunner;
    GamerAppRunner gamerAppRunner;

    @BeforeEach
    void setUp() {
        restHelper = new RestHelper();
        tetrisAppRunner = new TetrisAppRunner();
        gamerAppRunner = new GamerAppRunner();
    }

    @AfterEach
    void tearDown() {
        tetrisAppRunner.stop();
        gamerAppRunner.stop();
    }

    @Test
    void playWholeGameTestWithRandomMovement() throws InterruptedException {


        final String token = restHelper.callStartGameWithTestUser(TEST_USER);

        int responseStatus;

        do {
            responseStatus = controllTetris(restHelper, token);
            Thread.sleep(10);
        } while (responseStatus == HttpStatus.OK.value());
    }

    private int controllTetris(RestHelper restHelper, String token) {
        try {
            return restHelper.callControlWithTestUserAndToken(TEST_USER, token,
                    Movement.values()[new Random().nextInt(Movement.values().length)]).getStatusCodeValue();
        } catch (RestClientResponseException e) {
            Assertions.assertEquals(400, e.getRawStatusCode());
            Assertions.assertEquals("NOT_YOUR_GAME", e.getResponseBodyAsString());
            return e.getRawStatusCode();
        }
    }

}
