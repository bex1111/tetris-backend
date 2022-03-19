package org.bexterlab.tetrisbackend.gateway.socket;

import org.bexterlab.tetrisbackend.core.Logger;
import org.bexterlab.tetrisbackend.core.TrackSender;
import org.bexterlab.tetrisbackend.entity.Game;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class WebsocketsHandler extends TextWebSocketHandler implements TrackSender {

    // fixme test me
    private final CopyOnWriteArrayList<WebSocketSession> socketDtoList;
    private final GameToSocketTextMapper gameToSocketTextMapper;
    private final Logger logger;

    public WebsocketsHandler(CopyOnWriteArrayList<WebSocketSession> socketDtoList,
                             GameToSocketTextMapper gameToSocketTextMapper,
                             Logger logger) {
        this.socketDtoList = socketDtoList;
        this.gameToSocketTextMapper = gameToSocketTextMapper;
        this.logger = logger;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        logger.error("Error occurred at sender! " + session, throwable);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info(String.format("Session %s closed because of %s close status code", session.getId(), status.getCode()));
        socketDtoList.removeAll(socketDtoList.stream()
                .filter(x -> x.getId().equals(session.getId()))
                .collect(Collectors.toList()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Connected " + session);
        socketDtoList.add(session);
    }

    @Override
    public void sendTrackForUser(List<Game> games) {
        String gamesString = gameToSocketTextMapper.map(games);
        socketDtoList.forEach(x -> sendMessage(gamesString, x));
    }

    private void sendMessage(String text, WebSocketSession session) {
        try {
            TextMessage textMessage = new TextMessage(text);
            session.sendMessage(textMessage);
        } catch (IOException e) {
            logger.error("Error occurred while send socket message:", e);
        }
    }
}
