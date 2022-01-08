package org.bexterlab.tetrisbackend.helper;

import org.bexterlab.tetrisbackend.controller.dto.StartGameDto;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

public class RestHelper {

    private final RestTemplate restTemplate;

    public RestHelper() {
        restTemplate = new RestTemplate();
    }

    public String callStartGameWithTestUser(String username) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/startGame",
                new StartGameDto(username), String.class);
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        return responseEntity.getBody();
    }

    public void callHeath() {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:8080/health", Void.class);
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
    }

    public <T> void callControlWithTestUserAndToken(String username, String token, T movement) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(
                new MultiValueMapAdapter<>(Map.of("x-username", List.of(username),
                        "x-token", List.of(token))));
        ResponseEntity<Void> responseEntity = restTemplate.exchange(UriComponentsBuilder
                        .fromHttpUrl("http://localhost:8080/control")
                        .queryParam("movement", movement)
                        .build()
                        .toUri(),
                HttpMethod.POST, httpEntity, Void.class);
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
    }


}
