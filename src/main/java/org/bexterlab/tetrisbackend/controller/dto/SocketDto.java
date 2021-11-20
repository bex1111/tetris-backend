package org.bexterlab.tetrisbackend.controller.dto;

import org.springframework.web.socket.WebSocketSession;

public record SocketDto(String userName, String sessionId, WebSocketSession webSocketSession) {
}
