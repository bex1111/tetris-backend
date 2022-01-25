package org.bexterlab.tetrisbackend.core;

import org.awaitility.core.ConditionFactory;
import org.bexterlab.tetrisbackend.configuration.MainConfiguration;
import org.bexterlab.tetrisbackend.core.exception.UnexpectedGameStopException;
import org.bexterlab.tetrisbackend.core.mock.*;
import org.bexterlab.tetrisbackend.entity.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

class AsyncGameRunnerInteractorTest {

    private GameIntercatorSpy trackHandlerSpy;
    private GameStoreFake gameStoreFake;
    private TrackSenderFake trackSenderFake;
    private ExecuterServiceSpy executorServiceSpy;
    private AsyncGameRunnerInteractor asyncGameRunnerInteractor;
    private int gameTickTime;

    @BeforeEach
    void setUp() {
        executorServiceSpy = new ExecuterServiceSpy();
        trackHandlerSpy = new GameIntercatorSpy();
        gameStoreFake = new GameStoreFake();
        trackSenderFake = new TrackSenderFake();
        gameTickTime = 100;
        asyncGameRunnerInteractor = new AsyncGameRunnerInteractor(trackSenderFake,
                executorServiceSpy,
                trackHandlerSpy,
                gameStoreFake,
                new MainConfiguration().logger(),
                new DelayerDummy(gameTickTime)
        );
    }

    @Test
    void startGameTest() {
        gameStoreFake.hasGame = true;
        gameStoreFake.game = new Game();
        int callCount = 5;
        asyncGameRunnerInteractor.startGame();
        pollDelay()
                .atLeast((callCount - 1L) * gameTickTime, TimeUnit.MILLISECONDS)
                .atMost((callCount + 1L) * gameTickTime, TimeUnit.MILLISECONDS)
                .until(() -> trackHandlerSpy.maintenanceCallCount.get() >= callCount);
        gameStoreFake.hasGame = false;
        Assertions.assertTrue(trackSenderFake
                        .gameList
                        .stream()
                        .allMatch(x -> gameStoreFake.game.equals(x)),
                trackSenderFake.gameList.toString());
        Assertions.assertEquals(callCount, trackSenderFake.gameList.size());
        Assertions.assertEquals(callCount, trackHandlerSpy.maintenanceCallCount.get());
        pollDelay()
                .atMost(2L * gameTickTime, TimeUnit.MILLISECONDS)
                .until(() -> executorServiceSpy.future.isDone());
    }

    @Test
    void notStartGameTest() {
        gameStoreFake.hasGame = false;
        asyncGameRunnerInteractor.startGame();
        await().pollDelay(gameTickTime * 2L, TimeUnit.MILLISECONDS)
                .atMost(gameTickTime * 5L + 1L, TimeUnit.MILLISECONDS)
                .until(() -> trackHandlerSpy.maintenanceCallCount.get() == 0);
        Assertions.assertEquals(0L, trackSenderFake.gameList.size());
        Assertions.assertEquals(0L, trackHandlerSpy.maintenanceCallCount.get());
        Assertions.assertTrue(executorServiceSpy.future.isDone());
    }

    @Test
    void unexpectedGameStopExceptionTest() {
        trackSenderFake.exception = new RuntimeException("Hups");
        gameStoreFake.hasGame = true;
        gameStoreFake.game = new Game();
        asyncGameRunnerInteractor.startGame();
        await().pollDelay(gameTickTime * 2L, TimeUnit.MILLISECONDS)
                .atMost(gameTickTime * 5L + 1L, TimeUnit.MILLISECONDS)
                .until(() -> trackHandlerSpy.maintenanceCallCount.get() > 0);
        try {
            executorServiceSpy.future.get();
        } catch (ExecutionException | InterruptedException e) {
            Assertions.assertEquals(ExecutionException.class, e.getClass());
            Assertions.assertEquals(UnexpectedGameStopException.class, e.getCause().getClass());
        }
    }

    private ConditionFactory pollDelay() {
        return await().pollDelay(gameTickTime / 2, TimeUnit.MILLISECONDS);
    }
}