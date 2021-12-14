package org.bexterlab.tetrisbackend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bexterlab.tetrisbackend.core.*;
import org.bexterlab.tetrisbackend.gateway.socket.GameToSocketTextMapper;
import org.bexterlab.tetrisbackend.gateway.socket.WebsocketHandler;
import org.bexterlab.tetrisbackend.gateway.store.StoreImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

@Configuration
public class MainConfiguration {

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("Tetris Logger");
    }

    @Bean
    public AsyncGameHandler asyncGameHandler(TrackHandler trackHandler,
                                             StoreImpl store,
                                             Logger logger,
                                             WebsocketHandler trackSender
    ) {
        return new AsyncGameHandler(trackSender, Executors.newSingleThreadExecutor(),
                trackHandler, store,
                logger, new Delayer(500L, logger));
    }

    @Bean
    public WebsocketHandler websocketHandler(Logger logger,
                                             GameToSocketTextMapper gameToSocketTextMapper) {
        return new WebsocketHandler(new CopyOnWriteArrayList<>(),
                gameToSocketTextMapper, logger);
    }

    @Bean
    public GameToSocketTextMapper gameToSocketTextMapper(ObjectMapper objectMapper) {
        return new GameToSocketTextMapper(objectMapper);
    }

    @Bean
    public StartGameInteractorImpl startGameInteractor(StoreImpl store,
                                                       AsyncGameHandler asyncGameHandler) {
        return new StartGameInteractorImpl(store, store, asyncGameHandler);
    }

    @Bean
    public TetrisStepFactory tetrisStepFactory() {
        return new TetrisStepFactory();
    }

    @Bean
    public TrackHandler trackHandler(StoreImpl store, Logger logger,
                                     TetrisStepFactory tetrisStepFactory) {
        return new TrackHandler(store, tetrisStepFactory, logger);
    }

    @Bean
    public StoreImpl gameStore() {
        return new StoreImpl(new CopyOnWriteArrayList<>());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
    }

}
