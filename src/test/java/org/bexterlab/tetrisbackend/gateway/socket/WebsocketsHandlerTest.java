package org.bexterlab.tetrisbackend.gateway.socket;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.gateway.socket.mock.GameToSocketTextMapperSpy;
import org.bexterlab.tetrisbackend.gateway.socket.mock.WebsocketSessionFake;
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
        websocketsHandler.handleTransportError(new WebsocketSessionFake(), runtimeException);
        Assertions.assertEquals(runtimeException, loggerSpy.throwable);
        Assertions.assertEquals("Error occurred at sender! testSession", loggerSpy.object);
    }

    @Test
    void afterConnectionClosedTest() throws Exception {
        socketDtoList.add(new WebsocketSessionFake());
        String id2 = "id2";
        socketDtoList.add(new WebsocketSessionFake(id2));
        websocketsHandler.afterConnectionClosed(socketDtoList.get(0), CloseStatus.BAD_DATA);
        Assertions.assertEquals("Session id closed because of 1007 close status code", loggerSpy.object);
        Assertions.assertEquals(1L, socketDtoList.size());
        Assertions.assertEquals(id2, socketDtoList.get(0).getId());
    }

    @Test
    void afterConnectionEstablishedTest() throws Exception {
        WebsocketSessionFake websocketSessionFake = new WebsocketSessionFake();
        websocketsHandler.afterConnectionEstablished(websocketSessionFake);
        Assertions.assertEquals("Connected testSession", loggerSpy.object);
        Assertions.assertEquals(1, socketDtoList.size());
        Assertions.assertEquals(websocketSessionFake, socketDtoList.get(0));
    }

    @Test
    void sendTrackForUserTest() {
        socketDtoList.add(new WebsocketSessionFake());
        socketDtoList.add(new WebsocketSessionFake());
        List<Game> gameList = List.of(new Game(), new Game());
        websocketsHandler.sendTrackForUser(gameList);
        Assertions.assertEquals(gameList, gameToSocketTextMapper.games);
        Assertions.assertEquals(2, socketDtoList.size());
        Assertions.assertEquals("testMappedMessage", castToWebsocketSessionSpy(socketDtoList.get(0)));
        Assertions.assertEquals("testMappedMessage", castToWebsocketSessionSpy(socketDtoList.get(1)));
    }

    @Test
    void sendTrackForUserIOExceptionTest() {
        WebsocketSessionFake websocketSessionFake = new WebsocketSessionFake();
        websocketSessionFake.exception = new IOException();
        socketDtoList.add(websocketSessionFake);
        List<Game> gameList = List.of(new Game());
        websocketsHandler.sendTrackForUser(gameList);
        Assertions.assertEquals("Error occurred while send socket message:", loggerSpy.object);
        Assertions.assertEquals(websocketSessionFake.exception, loggerSpy.throwable);
    }


    private String castToWebsocketSessionSpy(WebSocketSession webSocketSession) {
        return (String) ((WebsocketSessionFake) webSocketSession).websocketMessageList.get(0).getPayload();
    }
}