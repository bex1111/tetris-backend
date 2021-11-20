package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.controller.dto.SocketDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class WebsocketHandler extends TextWebSocketHandler {

    private List<SocketDto> socketDtoList = new CopyOnWriteArrayList<>();
    private final Logger logger;

    public WebsocketHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.error("eError occurred at sender " + session, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
        socketDtoList = socketDtoList
                .stream()
                .filter(x -> !x.sessionId().equals(session.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (isNull(session.getHandshakeHeaders().get("x-user-name"))) {
            throw new IllegalArgumentException("x-user-name headers required");
        }
        SocketDto socketDto = new SocketDto(String.join("",
                session.getHandshakeHeaders().get("x-user-name")),
                session.getId(), session);
        logger.info("Connected ... " + socketDto);
        socketDtoList.add(socketDto);
    }

    public void sendMessage(String userName, String text) {
        socketDtoList.stream()
                .filter(x -> x.userName().equals(userName))
                .forEach(x -> sendMessage(text, x));
    }

    private void sendMessage(String text, SocketDto socketDto) {
        try {
            TextMessage textMessage = new TextMessage(text);
            socketDto.webSocketSession().sendMessage(textMessage);
        } catch (IOException e) {
            logger.error("Error occurred while send socket message:", e);
        }
    }
}
