package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.exception.TetrisException;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.Objects.isNull;

public class AsyncGameHandler {

    private final TrackSender trackSender;
    private final ExecutorService executor;
    private final TrackHandler trackHandler;
    private final GameStore gameStore;
    private final Logger logger;
    private final long gameTickTime;
    private long lastRunTime;
    private Future<Void> future;

    public AsyncGameHandler(TrackSender trackSender, ExecutorService executor,
                            TrackHandler trackHandler, GameStore gameStore,
                            Logger logger, long gameTickTime) {
        this.trackSender = trackSender;
        this.executor = executor;
        this.trackHandler = trackHandler;
        this.gameStore = gameStore;
        this.logger = logger;
        this.gameTickTime = gameTickTime;
        this.lastRunTime = System.currentTimeMillis();
    }


    public void startGame() {
        if (hasARunningThread()) {
            future = executor.submit(() -> {
                try {
                    runGame();
                } catch (TetrisException e) {
                    logger.error("Good exception here: ", e);
                } catch (Throwable e) {
                    logger.error("Bad exception here:", e);
                    future.cancel(true);
                }
                return null;
            });
        }
    }

    private boolean hasARunningThread() {
        return isNull(future) || future.isDone();
    }

    private void runGame() {
        while (gameStore.hasGame()) {
            trackHandler.maintenanceTracks();
            gameStore.getGames().forEach(trackSender::sendTrackForUser);
            sleepThread();
        }
    }

    private void sleepThread() {
        try {
            final long now = System.currentTimeMillis();
            final long currentDelay = gameTickTime - (now - lastRunTime); //FIXME nem jól számol
            lastRunTime = now;
            Thread.sleep(currentDelay < 0 ? gameTickTime : currentDelay);
            logger.info("Sleep: " + (currentDelay < 0 ? gameTickTime : currentDelay));
        } catch (InterruptedException e) {
            logger.error("sleepThread: " + e);
            throw new RuntimeException(e);
        }
    }
}
