package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.gateway.GameStore;
import org.bexterlab.tetrisbackend.core.gateway.Logger;
import org.bexterlab.tetrisbackend.core.gateway.TrackSender;
import org.bexterlab.tetrisbackend.exception.TetrisException;
import org.bexterlab.tetrisbackend.exception.UnexpectedGameStopException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.Objects.isNull;

public class AsyncGameRunnerInteractor {

    private final TrackSender trackSender;
    private final ExecutorService executor;
    private final GameIntercator gameIntercator;
    private final GameStore gameStore;
    private final Logger logger;
    private final Delayer delayer;
    private Future<Void> future;

    public AsyncGameRunnerInteractor(TrackSender trackSender, ExecutorService executor,
                                     GameIntercator gameIntercator, GameStore gameStore,
                                     Logger logger, Delayer delayer) {
        this.trackSender = trackSender;
        this.executor = executor;
        this.gameIntercator = gameIntercator;
        this.gameStore = gameStore;
        this.logger = logger;
        this.delayer = delayer;
    }


    public void startGame() {
        if (hasNotRunningThread()) {
            future = executor.submit(() -> {
                try {
                    runGame();
                } catch (TetrisException e) {
                    logger.error("Known exception while run game: ", e);
                } catch (Throwable e) {
                    logger.error("Not known exception while run game: ", e);
                    throw new UnexpectedGameStopException(e);
                }
                return null;
            });
        }
    }

    private boolean hasNotRunningThread() {
        return isNull(future) || future.isDone();
    }

    private void runGame() {
        while (gameStore.hasGame()) {
            gameIntercator.maintenanceTracks();
            trackSender.sendTrackForUser(gameStore.findGames());
            delayer.delay();
        }
    }
}
