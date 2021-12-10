package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.configuration.MainConfiguration;
import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.TrackHandlerSpy;
import org.bexterlab.tetrisbackend.core.mock.TrackSenderSpy;
import org.bexterlab.tetrisbackend.entity.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

class AsyncGameHandlerTest {

    private TrackHandlerSpy trackHandlerSpy;
    private GameStoreFake gameStoreFake;
    private MainConfiguration mainConfiguration;
    private TrackSenderSpy trackSenderSpy;

    @Test
    void startGameTest() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        trackHandlerSpy = new TrackHandlerSpy();
        gameStoreFake = new GameStoreFake();
        mainConfiguration = new MainConfiguration();
        trackSenderSpy = new TrackSenderSpy();
        int gameTickTime = 50;
        AsyncGameHandler asyncGameHandler = new AsyncGameHandler(trackSenderSpy,
                executor,
                trackHandlerSpy,
                gameStoreFake,
                mainConfiguration.logger(),
                gameTickTime);
        gameStoreFake.hasGame = true;
        gameStoreFake.game = new Game(null, null, null, null);
        int callCount = 5;
        asyncGameHandler.startGame();
        await().atLeast((callCount - 1) * gameTickTime, TimeUnit.MILLISECONDS)
                .atMost(callCount * gameTickTime, TimeUnit.MILLISECONDS)
                .until(() -> trackSenderSpy.gameList.size() == callCount);
        gameStoreFake.hasGame = false;
        Assertions.assertTrue(trackSenderSpy
                        .gameList
                        .stream()
                        .allMatch(x -> gameStoreFake.game.equals(x)),
                trackSenderSpy.gameList.toString());
        Assertions.assertEquals(callCount, trackSenderSpy.gameList.size());
        Assertions.assertEquals(callCount, trackHandlerSpy.maintenanceCallCount.get());
        Assertions.assertTrue(executor.isTerminated());
    }


    @Test
    void notStartGameTest() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        trackHandlerSpy = new TrackHandlerSpy();
        gameStoreFake = new GameStoreFake();
        mainConfiguration = new MainConfiguration();
        trackSenderSpy = new TrackSenderSpy();
        int gameTickTime = 50;
        AsyncGameHandler asyncGameHandler = new AsyncGameHandler(trackSenderSpy,
                executor,
                trackHandlerSpy,
                gameStoreFake,
                mainConfiguration.logger(),
                gameTickTime);
        gameStoreFake.hasGame = false;
        int callCount = 5;
        asyncGameHandler.startGame();
        await().atLeast((callCount - 1) * gameTickTime, TimeUnit.MILLISECONDS)
                .atMost(callCount * gameTickTime, TimeUnit.MILLISECONDS)
                .until(() -> trackSenderSpy.gameList.size() == 0);
        Assertions.assertEquals(0, trackSenderSpy.gameList.size());
        Assertions.assertEquals(0, trackHandlerSpy.maintenanceCallCount.get());
        Assertions.assertTrue(executor.isTerminated());
    }
}