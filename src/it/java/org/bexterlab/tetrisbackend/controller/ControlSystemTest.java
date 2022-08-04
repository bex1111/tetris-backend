package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.core.move.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClientResponseException;

class ControlSystemTest extends BaseControllerTest {

    private String token;

    @Override
    protected void localSetUp() {
        token = restHelper.callStartGameWithTestUser(TEST_USER);
    }

    @Test
    public void successTest() {
        Assertions.assertDoesNotThrow(() -> restHelper.callControlWithTestUserAndToken(TEST_USER, token, Movement.MOVE_LEFT));
    }

    @Test
    public void notYourGameWrongUsernameTest() {
        try {
            restHelper.callControlWithTestUserAndToken("NOT_TEST_USER", token, Movement.MOVE_LEFT);
        } catch (RestClientResponseException e) {
            Assertions.assertEquals(400, e.getRawStatusCode());
            Assertions.assertEquals("NOT_YOUR_GAME", e.getResponseBodyAsString());
        }
    }

    @Test
    public void notYourGameWrongTokenTest() {
        try {
            restHelper.callControlWithTestUserAndToken(TEST_USER, "INVALID_TOKEN", Movement.MOVE_LEFT);
        } catch (RestClientResponseException e) {
            Assertions.assertEquals(400, e.getRawStatusCode());
            Assertions.assertEquals("NOT_YOUR_GAME", e.getResponseBodyAsString());
        }
    }

    @Test
    public void wrongMovementTest() {
        try {
            restHelper.callControlWithTestUserAndToken(TEST_USER, token, "INVALID_MOVEMENT");
        } catch (RestClientResponseException e) {
            Assertions.assertEquals(500, e.getRawStatusCode());
            Assertions.assertEquals("CALL_BARNA_FOR_MORE_DETAILS|Failed to convert value of type " +
                            "'java.lang.String' to required type " +
                            "'org.bexterlab.tetrisbackend.core.move.Movement'; " +
                            "nested exception is org.springframework.core.convert.ConversionFailedException: " +
                            "Failed to convert from type [java.lang.String] to type" +
                            " [@org.springframework.web.bind.annotation.RequestParam org.bexterlab.tetrisbackend.core.move.Movement]" +
                            " for value 'INVALID_MOVEMENT'; nested exception is java.lang.IllegalArgumentException:" +
                            " No enum constant org.bexterlab.tetrisbackend.core.move.Movement.INVALID_MOVEMENT",
                    e.getResponseBodyAsString());
        }
    }

    @Test
    public void tooManyMovementTest() {
        try {
            for (int i = 0; i < 35; i++) {
                restHelper.callControlWithTestUserAndToken(TEST_USER, token, Movement.MOVE_LEFT);
            }
        } catch (RestClientResponseException e) {
            Assertions.assertEquals(400, e.getRawStatusCode());
            Assertions.assertEquals("TOO_MANY_MOVEMENT", e.getResponseBodyAsString());
        }
    }
}
