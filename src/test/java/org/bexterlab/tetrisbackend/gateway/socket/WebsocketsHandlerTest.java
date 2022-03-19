package org.bexterlab.tetrisbackend.gateway.socket;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.gateway.socket.mock.GameToSocketTextMapperSpy;
import org.bexterlab.tetrisbackend.gateway.socket.mock.WebsocketSessionSpy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class WebsocketsHandlerTest {

    private WebsocketsHandler websocketsHandler;
    private CopyOnWriteArrayList<WebSocketSession> socketDtoList;
    private GameToSocketTextMapperSpy gameToSocketTextMapper;
    private LoggerSpy loggerSpy;

    @BeforeEach
    void setUp() {
        socketDtoList = new CopyOnWriteArrayList<>();
        loggerSpy = new LoggerSpy();
        gameToSocketTextMapper = new GameToSocketTextMapperSpy();
        websocketsHandler = new WebsocketsHandler(socketDtoList, gameToSocketTextMapper, loggerSpy);
    }

    @Test
    void handleTransportErrorTest() throws Exception {
        RuntimeException runtimeException = new RuntimeException();
        websocketsHandler.handleTransportError(new WebsocketSessionSpy(), runtimeException);
        Assertions.assertEquals(runtimeException, loggerSpy.throwable);
        Assertions.assertEquals("Error occurred at sender! testSession", loggerSpy.object);
    }

    @Test
    void afterConnectionClosedTest() throws Exception {
        socketDtoList.add(new WebsocketSessionSpy());
        websocketsHandler.afterConnectionClosed(new WebsocketSessionSpy(), CloseStatus.BAD_DATA);
        Assertions.assertEquals("Session id closed because of 1007 close status code", loggerSpy.object);
        Assertions.assertTrue(socketDtoList.isEmpty());
    }

    @Test
    void afterConnectionEstablishedTest() throws Exception {
        WebsocketSessionSpy websocketSessionSpy = new WebsocketSessionSpy();
        websocketsHandler.afterConnectionEstablished(websocketSessionSpy);
        Assertions.assertEquals("Connected testSession", loggerSpy.object);
        Assertions.assertEquals(1, socketDtoList.size());
        Assertions.assertEquals(websocketSessionSpy, socketDtoList.get(0));
    }

    @Test
    void sendTrackForUserTest() {
        socketDtoList.add(new WebsocketSessionSpy());
        socketDtoList.add(new WebsocketSessionSpy());
        List<Game> gameList = List.of(new Game(), new Game());
        websocketsHandler.sendTrackForUser(gameList);
        Assertions.assertEquals(gameList, gameToSocketTextMapper.games);
        Assertions.assertEquals(2, socketDtoList.size());
        Assertions.assertEquals("testMappedMessage", castToWebsocketSessionSpy(socketDtoList.get(0)));
        Assertions.assertEquals("testMappedMessage", castToWebsocketSessionSpy(socketDtoList.get(1)));
    }

    @Test
    void sendTrackForUserIOExceptionTest() {
        WebsocketSessionSpy websocketSessionSpy = new WebsocketSessionSpy();
        websocketSessionSpy.exception = new IOException();
        socketDtoList.add(websocketSessionSpy);
        List<Game> gameList = List.of(new Game());
        websocketsHandler.sendTrackForUser(gameList);
        Assertions.assertEquals("Error occurred while send socket message:", loggerSpy.object);
        Assertions.assertEquals(websocketSessionSpy.exception, loggerSpy.throwable);
    }


    private String castToWebsocketSessionSpy(WebSocketSession webSocketSession) {
        return (String) ((WebsocketSessionSpy) webSocketSession).websocketMessageList.get(0).getPayload();
    }
}