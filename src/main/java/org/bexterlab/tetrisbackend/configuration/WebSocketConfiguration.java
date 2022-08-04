package org.bexterlab.tetrisbackend.configuration;

import org.bexterlab.tetrisbackend.gateway.socket.WebsocketsHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

    private final WebsocketsHandler websocketsHandler;

    public WebSocketConfiguration(WebsocketsHandler websocketsHandler) {
        this.websocketsHandler = websocketsHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(websocketsHandler, "/api/tetris").setAllowedOrigins("*");
    }
}
