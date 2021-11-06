package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.TetrisApplication;
import org.bexterlab.tetrisbackend.controller.dto.StartGameDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

class StartGameIntTest {

    public static final String UUID_REGEXP = "[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
    private final RestTemplate restTemplate = new RestTemplate();
    private ConfigurableApplicationContext configurableApplicationContext;

    @BeforeEach
    void setUp() {
        configurableApplicationContext = SpringApplication.run(TetrisApplication.class);
    }

    @AfterEach
    void tearDown() {
        configurableApplicationContext.close();
    }

    @Test
    public void successTest() {
        ResponseEntity<String> responseEntity =
                callStartGameWithTestUser();
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
        Assertions.assertDoesNotThrow(() -> UUID.fromString(responseEntity.getBody()), responseEntity.getBody());
    }

    @Test
    public void sameUserTest() {
        successTest();
        try {
            callStartGameWithTestUser();
        } catch (RestClientResponseException e) {
            Assertions.assertEquals(400, e.getRawStatusCode());
            Assertions.assertEquals("YOU_ALREADY_HAVE_A_GAME", e.getResponseBodyAsString());
        }

    }

    private ResponseEntity<String> callStartGameWithTestUser() {
        return restTemplate.postForEntity("http://localhost:8080/startGame",
                new StartGameDto("test_user"), String.class);
    }
}