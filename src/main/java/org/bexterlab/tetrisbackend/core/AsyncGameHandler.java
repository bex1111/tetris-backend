package org.bexterlab.tetrisbackend.core;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.Objects.isNull;

public class AsyncGameHandler {

    private static final long THREAD_RUN_TIME = 1000;
    private static final long MOVING_AVERAGE_SIZE = 5;

    private final TrackSender trackSender;
    private final ExecutorService executor;
    private final List<Long> runTimeList;
    private final TrackHandler trackHandler;
    private final GameStore gameStore;
    private final Logger logger;
    private Future<Void> future;

    public AsyncGameHandler(TrackSender trackSender, ExecutorService executor,
                            TrackHandler trackHandler,
                            GameStore gameStore, Logger logger) {
        this.trackSender = trackSender;
        this.executor = executor;
        this.trackHandler = trackHandler;
        this.gameStore = gameStore;
        this.logger = logger;
        runTimeList = Collections.synchronizedList(new ArrayList<>());
    }


    public void startGame() {
        if (isNull(future) || future.isDone()) {
            future = executor.submit(() -> {
                while (gameStore.hasGame()) {
                    long start = System.currentTimeMillis();
                    trackHandler.maintenanceTracks();
                    gameStore.getGames().forEach(trackSender::sendTrackForUser);
                    calculateRunTime(start);
                    sleepThread();
                }
                return null;
            });
        }
    }

    private void sleepThread() {
        try {
            Thread.sleep(runTimeList
                    .stream()
                    .reduce(Long::sum)
                    .orElse(THREAD_RUN_TIME)
                    / runTimeList.size());
        } catch (InterruptedException e) {
            logger.error("sleepThread: " + e);
        }
    }

    private void calculateRunTime(long start) {
        long runTime = System.currentTimeMillis() - start;
        if (runTimeList.size() > MOVING_AVERAGE_SIZE) {
            runTimeList.remove(0);
        }
        runTimeList.add(runTime > THREAD_RUN_TIME ? 0 : THREAD_RUN_TIME - runTime);
        logger.info(String.format("Last %s runtimes: %s", MOVING_AVERAGE_SIZE, runTimeList));
    }
}
