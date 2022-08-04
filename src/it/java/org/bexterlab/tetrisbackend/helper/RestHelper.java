package org.bexterlab.tetrisbackend.helper;

import org.bexterlab.tetrisbackend.controller.dto.PointsDto;
import org.bexterlab.tetrisbackend.controller.dto.StartGameDto;
import org.junit.jupiter.api.Assertions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

import static org.bexterlab.tetrisbackend.TestConstants.HTTP_BASE_URL;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

public class RestHelper {

    private final RestTemplate restTemplate;

    public RestHelper() {
        restTemplate = new RestTemplate();
    }

    public String callStartGameWithTestUser(String username) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(HTTP_BASE_URL + "/startGame",
                new StartGameDto().setUsername(username), String.class);
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        return responseEntity.getBody();
    }

    public List<PointsDto> callListPoints() {
        ResponseEntity<List<PointsDto>> responseEntity = restTemplate.exchange(HTTP_BASE_URL + "/api/listPoints",
                GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {
                });
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        return responseEntity.getBody();
    }

    public void callHeath() {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity(HTTP_BASE_URL + "/health", Void.class);
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
    }

    public <T> ResponseEntity<Void> callControlWithTestUserAndToken(String username, String token, T movement) {
        HttpEntity<Void> httpEntity = new HttpEntity<>(
                new MultiValueMapAdapter<>(Map.of("x-username", List.of(username),
                        "x-token", List.of(token))));
        ResponseEntity<Void> responseEntity = restTemplate.exchange(UriComponentsBuilder
                        .fromHttpUrl(HTTP_BASE_URL + "/control")
                        .queryParam("movement", movement)
                        .build()
                        .toUri(),
                HttpMethod.POST, httpEntity, Void.class);
        Assertions.assertEquals(OK, responseEntity.getStatusCode());
        return responseEntity;
    }


}
