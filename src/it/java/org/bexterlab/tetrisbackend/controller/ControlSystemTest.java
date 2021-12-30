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
            Assertions.assertEquals("CALL_BARNA", e.getResponseBodyAsString());
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
