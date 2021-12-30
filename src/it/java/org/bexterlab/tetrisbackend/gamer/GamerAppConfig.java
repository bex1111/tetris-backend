package org.bexterlab.tetrisbackend.gamer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.ExecutionException;

import static org.bexterlab.tetrisbackend.TestConstants.TEST_USER;

@Configuration
@Profile("Gamer")
public class GamerAppConfig {

    @Bean
    public ObjectMapper gamerAppObjectMapper() {
        return new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
    }

    @Bean
    public Logger gamerAppLogger() {
        return LoggerFactory.getLogger("Gamer App Logger");
    }

    @Bean
    public GamerAppStompSessionHandler gamerAppWebsocketHandler(Logger gamerAppLogger, ObjectMapper gamerAppObjectMapper) {
        return new GamerAppStompSessionHandler(gamerAppLogger, gamerAppObjectMapper);
    }

    @Bean
    public WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    public WebSocketStompClient webSocketStompClient(WebSocketClient webSocketClient,
                                                     GamerAppStompSessionHandler gamerAppStompSessionHandler) throws ExecutionException, InterruptedException {
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-username", TEST_USER);
        WebSocketHttpHeaders webSocketHttpHeaders = new WebSocketHttpHeaders(httpHeaders);

        ListenableFuture<StompSession> stompSessionListenableFuture = stompClient.connect("ws://localhost:8080/tetris",
                webSocketHttpHeaders,
                gamerAppStompSessionHandler);
        stompClient.setAutoStartup(true);
        stompSessionListenableFuture.addCallback(
                stompSession -> stompSession.subscribe("/tetris", gamerAppStompSessionHandler),
                Throwable::printStackTrace);
        stompClient.start();
        return stompClient;
    }


}
