package org.bexterlab.tetrisbackend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bexterlab.tetrisbackend.controller.ControlInteractor;
import org.bexterlab.tetrisbackend.controller.ListPointsInteractor;
import org.bexterlab.tetrisbackend.core.*;
import org.bexterlab.tetrisbackend.core.gateway.Logger;
import org.bexterlab.tetrisbackend.core.gateway.MovementStore;
import org.bexterlab.tetrisbackend.core.gateway.UserStore;
import org.bexterlab.tetrisbackend.core.steps.BaseSteps;
import org.bexterlab.tetrisbackend.core.steps.GameEndSteps;
import org.bexterlab.tetrisbackend.core.steps.NotTetrisElementInTrackSteps;
import org.bexterlab.tetrisbackend.gateway.UserInformationStoreImpl;
import org.bexterlab.tetrisbackend.gateway.log.LoggerImpl;
import org.bexterlab.tetrisbackend.gateway.socket.GameToSocketTextMapper;
import org.bexterlab.tetrisbackend.gateway.socket.WebsocketsHandler;
import org.bexterlab.tetrisbackend.gateway.store.GameStoreImpl;
import org.bexterlab.tetrisbackend.gateway.store.ScoreBoardStoreImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

@Configuration
public class MainConfiguration {

    @Bean
    public Logger logger(ObjectMapper objectMapper) {
        return new LoggerImpl(LoggerFactory.getLogger("Tetris Logger"), objectMapper);
    }

    @Bean
    public AsyncGameRunnerInteractor asyncGameHandler(GameIntercator gameIntercator,
                                                      GameStoreImpl store,
                                                      Logger logger,
                                                      WebsocketsHandler trackSender,
                                                      Delayer delayer) {
        return new AsyncGameRunnerInteractor(trackSender,
                Executors.newSingleThreadExecutor(),
                gameIntercator, store,
                logger, delayer);
    }

    @Bean
    public Delayer delayer(Logger logger, @Value("${tetris.gameTickTime}") Long gameTickTime) {
        return new Delayer(gameTickTime, logger);
    }

    @Bean
    public WebsocketsHandler websocketHandler(Logger logger,
                                              GameToSocketTextMapper gameToSocketTextMapper) {
        return new WebsocketsHandler(new CopyOnWriteArrayList<>(),
                gameToSocketTextMapper,
                logger);
    }

    @Bean
    public GameToSocketTextMapper gameToSocketTextMapper(ObjectMapper objectMapper,
                                                         GameConfiguration gameConfiguration) {
        return new GameToSocketTextMapper(objectMapper, gameConfiguration.getDeadRowIndex());
    }

    @Bean
    public StartGameInteractorImpl startGameInteractor(GameStoreImpl store,
                                                       AsyncGameRunnerInteractor asyncGameRunnerInteractor,
                                                       TetrisStepFactory tetrisStepFactory,
                                                       GameConfiguration gameConfiguration,
                                                       UserInformationStoreImpl userInformationStore) {
        return new StartGameInteractorImpl(store,
                store,
                asyncGameRunnerInteractor,
                tetrisStepFactory,
                gameConfiguration,
                userInformationStore);
    }

    @Bean
    public TetrisStepFactory tetrisStepFactory() {
        return new TetrisStepFactory();
    }

    @Bean
    public GameIntercator trackHandler(GameStoreImpl store, Logger logger,
                                       GameEndSteps gameEndSteps,
                                       BaseSteps baseSteps,
                                       NotTetrisElementInTrackSteps notTetrisElementInTrackSteps) {
        return new GameIntercator(store, gameEndSteps,
                notTetrisElementInTrackSteps, baseSteps,
                logger);
    }

    @Bean
    public BaseSteps baseSteps(GameStoreImpl store,
                               TetrisStepFactory tetrisStepFactory) {
        return new BaseSteps(tetrisStepFactory, store);
    }

    @Bean
    public GameEndSteps gameEndSteps(GameStoreImpl store,
                                     GameConfiguration gameConfiguration,
                                     ScoreBoardStoreImpl scoreBoardStore,
                                     UserInformationStoreImpl userInformationStore) {
        return new GameEndSteps(store,
                store,
                gameConfiguration.getDeadRowIndex(),
                scoreBoardStore,
                userInformationStore);
    }

    @Bean
    public NotTetrisElementInTrackSteps notTetrisElementInTrackSteps(GameStoreImpl store,
                                                                     TetrisStepFactory tetrisStepFactory) {
        return new NotTetrisElementInTrackSteps(tetrisStepFactory, store, store);
    }

    @Bean
    public GameStoreImpl gameStore() {
        return new GameStoreImpl(new CopyOnWriteArrayList<>());
    }

    @Bean
    public UserInformationStoreImpl userInformationStore() {
        return new UserInformationStoreImpl(new ConcurrentHashMap<>());
    }

    @Bean
    public ScoreBoardStoreImpl scoreBoardStore() {
        return new ScoreBoardStoreImpl(new ConcurrentHashMap<>());
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .createXmlMapper(false)
                .build();
    }

    @Bean
    public ControlInteractor controlInteractor(MovementStore movementStore, UserStore userStore) {
        return new ControlInteractorImpl(movementStore, userStore);
    }

    @Bean
    public ListPointsInteractor listPointsInteractor(ScoreBoardStoreImpl scoreBoardStore) {
        return new ListPointsInteractorImpl(scoreBoardStore);
    }

    @Bean
    @ConfigurationProperties(prefix = "tetris")
    public GameConfiguration gameConfiguration() {
        return new GameConfiguration();
    }
}
