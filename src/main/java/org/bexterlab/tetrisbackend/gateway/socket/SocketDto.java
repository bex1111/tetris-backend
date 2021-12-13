package org.bexterlab.tetrisbackend.gateway.socket;

import org.springframework.web.socket.WebSocketSession;

public record SocketDto(String userName, String sessionId, WebSocketSession webSocketSession) {
}
