package org.bexterlab.tetrisbackend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bexterlab.tetrisbackend.controller.ControlInteractor;
import org.bexterlab.tetrisbackend.core.*;
import org.bexterlab.tetrisbackend.core.steps.BaseSteps;
import org.bexterlab.tetrisbackend.core.steps.GameEndSteps;
import org.bexterlab.tetrisbackend.core.steps.NotTetrisElementInTrackSteps;
import org.bexterlab.tetrisbackend.gateway.socket.GameToSocketTextMapper;
import org.bexterlab.tetrisbackend.gateway.socket.WebsocketHandler;
import org.bexterlab.tetrisbackend.gateway.store.StoreImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    public AsyncGameRunnerInteractor asyncGameHandler(GameIntercator gameIntercator,
                                                      StoreImpl store,
                                                      Logger logger,
                                                      WebsocketHandler trackSender,
                                                      @Value("${tetris.gameTickTime}") Long gameTickTime) {
        return new AsyncGameRunnerInteractor(trackSender,
                Executors.newSingleThreadExecutor(),
                gameIntercator, store,
                logger, new Delayer(gameTickTime, logger));
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
                                                       AsyncGameRunnerInteractor asyncGameRunnerInteractor) {
        return new StartGameInteractorImpl(store, store, asyncGameRunnerInteractor);
    }

    @Bean
    public TetrisStepFactory tetrisStepFactory() {
        return new TetrisStepFactory();
    }

    @Bean
    public GameIntercator trackHandler(StoreImpl store, Logger logger,
                                       GameEndSteps gameEndSteps,
                                       BaseSteps baseSteps,
                                       NotTetrisElementInTrackSteps notTetrisElementInTrackSteps) {
        return new GameIntercator(store, gameEndSteps,
                notTetrisElementInTrackSteps, baseSteps,
                logger);
    }

    @Bean
    public BaseSteps baseSteps(StoreImpl store,
                               TetrisStepFactory tetrisStepFactory) {
        return new BaseSteps(tetrisStepFactory, store);
    }

    @Bean
    public GameEndSteps gameEndSteps(StoreImpl store, @Value("${tetris.deadRowIndex}") Long gameTickTime) {
        return new GameEndSteps(store, store, gameTickTime.intValue());
    }

    @Bean
    public NotTetrisElementInTrackSteps notTetrisElementInTrackSteps(StoreImpl store,
                                                                     TetrisStepFactory tetrisStepFactory) {
        return new NotTetrisElementInTrackSteps(tetrisStepFactory, store, store);
    }

    @Bean
    public StoreImpl gameStore(@Value("${tetris.maxUserCount}") int maxUserCount) {
        return new StoreImpl(new CopyOnWriteArrayList<>(),
                new CopyOnWriteArrayList<>(), maxUserCount);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
    }

    @Bean
    public ControlInteractor controlInteractor(MovementStore movementStore, UserStore userStore) {
        return new ControlInteractorImpl(movementStore, userStore);
    }

}
