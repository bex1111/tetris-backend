package org.bexterlab.tetrisbackend.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClientResponseException;

import java.util.UUID;

class StartGameSystemTest extends BaseControllerTest {

    @Test
    public void successTest() {
        String token = restHelper.callStartGameWithTestUser(TEST_USER);
        Assertions.assertDoesNotThrow(() -> UUID.fromString(token), token);
    }

    @Test
    public void sameUserTest() {
        successTest();
        try {
            restHelper.callStartGameWithTestUser(TEST_USER);
        } catch (RestClientResponseException e) {
            Assertions.assertEquals(400, e.getRawStatusCode());
            Assertions.assertEquals("YOU_ALREADY_HAVE_A_GAME", e.getResponseBodyAsString());
        }
    }

    @Test
    public void maxUserLimitTestUnderLimit() {
        for (int i = 0; i < 29; i++) {
            restHelper.callStartGameWithTestUser(TEST_USER + i);
        }
        String token = restHelper.callStartGameWithTestUser(TEST_USER);
        Assertions.assertDoesNotThrow(() -> UUID.fromString(token), token);
    }

    @Test
    public void maxUserLimitTestLimitReached() {
        for (int i = 0; i < 30; i++) {
            restHelper.callStartGameWithTestUser(TEST_USER + i);
        }
        try {
            restHelper.callStartGameWithTestUser(TEST_USER);
            throw new AssertionError("Exception not thrown");
        } catch (RestClientResponseException e) {
            Assertions.assertEquals(400, e.getRawStatusCode());
            Assertions.assertEquals("MAX_USER_COUNT_REACHED_TAKE_A_COFFEE_BREAK", e.getResponseBodyAsString());
        }
    }
}