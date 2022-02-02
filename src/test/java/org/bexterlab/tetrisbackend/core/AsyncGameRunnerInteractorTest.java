package org.bexterlab.tetrisbackend.core;

import org.awaitility.core.ConditionFactory;
import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.bexterlab.tetrisbackend.core.exception.CanNotMoveException;
import org.bexterlab.tetrisbackend.core.mock.*;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.exception.UnexpectedGameStopException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

class AsyncGameRunnerInteractorTest {

    private GameIntercatorSpy trackHandlerSpy;
    private GameStoreFake gameStoreFake;
    private TrackSenderFake trackSenderFake;
    private ExecuterServiceSpy executorServiceSpy;
    private AsyncGameRunnerInteractor asyncGameRunnerInteractor;
    private int gameTickTime;
    private LoggerSpy loggerSpy;

    @BeforeEach
    void setUp() {
        executorServiceSpy = new ExecuterServiceSpy();
        trackHandlerSpy = new GameIntercatorSpy();
        gameStoreFake = new GameStoreFake();
        trackSenderFake = new TrackSenderFake();
        gameTickTime = 100;
        loggerSpy = new LoggerSpy();
        asyncGameRunnerInteractor = new AsyncGameRunnerInteractor(trackSenderFake,
                executorServiceSpy,
                trackHandlerSpy,
                gameStoreFake,
                loggerSpy,
                new DelayerDummy(gameTickTime)
        );
    }

    @Test
    void startGameTest() {
        gameStoreFake.hasGame = true;
        gameStoreFake.game = new Game();

        int callCount = 5;

        asyncGameRunnerInteractor.startGame();

        pollDelay().pollDelay(gameTickTime / 2, TimeUnit.MILLISECONDS)
                .atLeast((callCount - 1L) * gameTickTime, TimeUnit.MILLISECONDS)
                .atMost((callCount + 2L) * gameTickTime, TimeUnit.MILLISECONDS)
                .until(() -> trackHandlerSpy.maintenanceCallCount.get() >= callCount);

        gameStoreFake.hasGame = false;

        Assertions.assertTrue(trackSenderFake.gameList.stream()
                        .allMatch(x -> gameStoreFake.game.equals(x)),
                trackSenderFake.gameList.toString());
        Assertions.assertEquals(callCount, trackSenderFake.gameList.size());
        Assertions.assertEquals(callCount, trackHandlerSpy.maintenanceCallCount.get());
        pollDelay().atMost(2L * gameTickTime, TimeUnit.MILLISECONDS)
                .until(() -> executorServiceSpy.future.isDone());
    }

    @Test
    void startTwoGameTest() {
        gameStoreFake.hasGame = true;
        gameStoreFake.game = new Game();

        asyncGameRunnerInteractor.startGame();
        Future<?> future = executorServiceSpy.future;
        asyncGameRunnerInteractor.startGame();
        Assertions.assertEquals(future, executorServiceSpy.future);
        gameStoreFake.hasGame = false;
        future.cancel(true);
        Assertions.assertTrue(future.isDone());
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
            Assertions.fail("Exception expected");
        } catch (ExecutionException | InterruptedException e) {
            Assertions.assertEquals(ExecutionException.class, e.getClass());
            Assertions.assertEquals(UnexpectedGameStopException.class, e.getCause().getClass());
            Assertions.assertEquals("Not known exception while run game: ", loggerSpy.object);
            Assertions.assertEquals(trackSenderFake.exception, loggerSpy.throwable);
        }
    }

    @Test
    void expectedGameStopExceptionTest() {
        trackSenderFake.exception = new CanNotMoveException();
        gameStoreFake.hasGame = true;
        gameStoreFake.game = new Game();
        asyncGameRunnerInteractor.startGame();
        await().pollDelay(gameTickTime * 2L, TimeUnit.MILLISECONDS)
                .atMost(gameTickTime * 5L + 1L, TimeUnit.MILLISECONDS)
                .until(() -> trackHandlerSpy.maintenanceCallCount.get() > 0);

        Assertions.assertTrue(executorServiceSpy.future.isDone());

        Assertions.assertEquals("Known exception while run game: ", loggerSpy.object);
        Assertions.assertEquals(trackSenderFake.exception, loggerSpy.throwable);

    }

    private ConditionFactory pollDelay() {
        return await().pollDelay(gameTickTime / 2, TimeUnit.MILLISECONDS);
    }
}