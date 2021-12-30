package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.TetrisApplication;
import org.bexterlab.tetrisbackend.controller.dto.StartGameDto;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

class ControlSystemTest {

    private static final String TEST_USER = "test_user";
    private String token;
    private final RestTemplate restTemplate;

    private ConfigurableApplicationContext configurableApplicationContext;

    public ControlSystemTest() {
        this.restTemplate = new RestTemplate();
    }

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
        Assertions.assertNotNull(responseEntity.getBody());
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
                new StartGameDto(TEST_USER), String.class);
    }

    private ResponseEntity<Void> callControlWithTestUserAndToken() {
        HttpEntity<Void> httpEntity = new HttpEntity<Void>(
                new MultiValueMapAdapter<String, String>(
                        Map.of("x-username", List.of(TEST_USER), "x-token", List.of(token))));
        return restTemplate.exchange("http://localhost:8080/control", HttpMethod.POST, httpEntity, Void.class,
                Map.of("movement", Movement.MOVE_LEFT));
    }
}
