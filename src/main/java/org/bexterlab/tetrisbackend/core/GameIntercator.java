package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.exception.CoreException;
import org.bexterlab.tetrisbackend.core.steps.BaseSteps;
import org.bexterlab.tetrisbackend.core.steps.GameEndSteps;
import org.bexterlab.tetrisbackend.core.steps.NotTetrisElementInTrackSteps;
import org.slf4j.Logger;

public class GameIntercator {

    private final GameStore gameStore;
    private final UserStore userStore;
    private final GameEndSteps gameEndSteps;
    private final NotTetrisElementInTrackSteps notTetrisElementInTrackSteps;
    private final Logger logger;
    private final BaseSteps baseSteps;


    public GameIntercator(GameStore gameStore, UserStore userStore,
                          GameEndSteps gameEndSteps,
                          NotTetrisElementInTrackSteps notTetrisElementInTrackSteps,
                          Logger logger,
                          BaseSteps baseSteps) {
        this.gameStore = gameStore;
        this.userStore = userStore;
        this.gameEndSteps = gameEndSteps;
        this.baseSteps = baseSteps;
        this.notTetrisElementInTrackSteps = notTetrisElementInTrackSteps;
        this.logger = logger;
    }

    public void maintenanceTracks() {
        this.userStore
                .findUsernames()
                .forEach(this::maintenanceTrack);
    }

    private void maintenanceTrack(String username) {
        try {
            doSteps(username);
        } catch (CoreException e) {
            logger.debug("Core exception occurred while maintenance track: ", e);
        } catch (Exception e) {
            logger.error("Exception occurred while maintenance track: ", e);
        }
    }

    private void doSteps(String username) {
        baseSteps.execute(username);
        notTetrisElementInTrackSteps.execute(username);
        gameEndSteps.execute(username);
    }
}
