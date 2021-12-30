package org.bexterlab.tetrisbackend.gateway.socket;

import org.bexterlab.tetrisbackend.core.TrackSender;
import org.bexterlab.tetrisbackend.entity.Game;
import org.slf4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class WebsocketHandler extends TextWebSocketHandler implements TrackSender {

    // fixme test me

    private final CopyOnWriteArrayList<SocketDto> socketDtoList;
    private final GameToSocketTextMapper gameToSocketTextMapper;
    private final Logger logger;

    public WebsocketHandler(CopyOnWriteArrayList<SocketDto> socketDtoList, GameToSocketTextMapper gameToSocketTextMapper, Logger logger) {
        this.socketDtoList = socketDtoList;
        this.gameToSocketTextMapper = gameToSocketTextMapper;
        this.logger = logger;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.error("Error occurred at sender " + session, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info(String.format("Session %s closed because of %s", session.getId(), status.getReason()));
        socketDtoList.removeAll(socketDtoList.stream()
                .filter(x -> x.sessionId().equals(session.getId()))
                .collect(Collectors.toList()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (isNull(session.getHandshakeHeaders().get("x-username"))) {
            throw new IllegalArgumentException("x-username headers required");
        }
        SocketDto socketDto = new SocketDto(String.join("",
                session.getHandshakeHeaders().get("x-username")),
                session.getId(), session);
        logger.info("Connected ... " + socketDto);
        socketDtoList.add(socketDto);
    }

    @Override
    public void sendTrackForUser(Game game) {
        socketDtoList.stream()
                .filter(x -> x.userName().equals(game.user().username()))
                .forEach(x -> sendMessage(gameToSocketTextMapper.map(game), x));
    }

    private void sendMessage(String text, SocketDto socketDto) {
        try {
            TextMessage textMessage = new TextMessage(text, true);
            socketDto.webSocketSession().sendMessage(textMessage);
        } catch (IOException e) {
            logger.error("Error occurred while send socket message:", e);
        }
    }
}
