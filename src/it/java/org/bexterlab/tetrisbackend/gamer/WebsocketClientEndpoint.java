package org.bexterlab.tetrisbackend.gamer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bexterlab.tetrisbackend.gateway.socket.GameToSocketTextMapper;
import org.slf4j.Logger;

import javax.websocket.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.List;

@ClientEndpoint
public class WebsocketClientEndpoint {

    private final ObjectMapper objectMapper;
    private final Logger logger;

    public WebsocketClientEndpoint(URI endpointURI, ObjectMapper objectMapper, Logger logger) {
        this.objectMapper = objectMapper;
        this.logger = logger;
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        logger.info("opening websocket" + userSession);
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        logger.info("closing websocket" + userSession.toString() + reason.toString());
    }


    @OnMessage
    public void onMessage(String message) {
        handleMessage(message);
    }

    @OnMessage
    public void onMessage(ByteBuffer bytes) {
        logger.warn("Handle byte buffer" + bytes.toString());
    }


    public void handleMessage(String message) {
        try {
            List<GameToSocketTextMapper.TrackDto> trackDtoList =
                    objectMapper.readValue(message, new TypeReference<>() {
                    });
            trackDtoList.forEach(x -> logTrackForDebug(x.track()));
        } catch (Exception e) {
            logger.error("handleFrame", e);
        }
    }

    private void logTrackForDebug(GameToSocketTextMapper.TrackElementDto[][] track) {
        StringBuilder finalText = new StringBuilder();
        for (GameToSocketTextMapper.TrackElementDto[] row : track) {
            finalText.append("\n");
            for (GameToSocketTextMapper.TrackElementDto column : row) {
                switch (column) {
                    case EMPTY -> {
                        finalText.append(" ");
                    }
                    case POINT -> {
                        finalText.append("P");
                    }
                    case ELEMENT -> {
                        finalText.append("E");
                    }
                }
            }
        }
        logger.info(finalText.toString());
    }
}