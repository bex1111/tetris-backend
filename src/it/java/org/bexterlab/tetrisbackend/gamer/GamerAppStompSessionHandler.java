package org.bexterlab.tetrisbackend.gamer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class GamerAppStompSessionHandler extends StompSessionHandlerAdapter {

    private final Logger logger;
    private final ObjectMapper objectMapper;

    public GamerAppStompSessionHandler(Logger logger, ObjectMapper objectMapper) {
        this.logger = logger;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/tetris", this);
        logger.info("Subscribed to /tetris");
//        session.send("/app/chat", getSampleMessage());
//        logger.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers,
                                byte[] payload, Throwable exception) {
        logger.error("handleException", exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        logger.error("handleTransportError", exception);
        super.handleTransportError(session, exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        try {
            return super.getPayloadType(headers);
        } catch (Exception e) {
            logger.error("getPayloadType", e);
        }
        return null;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        try {
            logTrackForDebug(objectMapper.readValue(payload
                            .toString()
                            .getBytes(StandardCharsets.UTF_8),
                    new TypeReference<List<List<String>>>() {
                    }));
        } catch (Exception e) {
            logger.error("handleFrame", e);
        }
    }

    private void logTrackForDebug(List<List<String>> track) {
        logger.info("\n" + "-".repeat(track.get(0).size()) + "\n" +
                track.stream()
                        .map(row -> String.join(" ", row))
                        .collect(Collectors.joining("|\n")) +
                "\n" + "-".repeat(track.get(0).size()) + "\n");
    }
}
