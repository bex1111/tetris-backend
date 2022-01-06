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
}