package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.exception.CoreException;
import org.bexterlab.tetrisbackend.core.steps.BaseSteps;
import org.bexterlab.tetrisbackend.core.steps.GameEndSteps;
import org.bexterlab.tetrisbackend.core.steps.NotTetrisElementInTrackSteps;
import org.bexterlab.tetrisbackend.exception.UnexpectedGameStopException;

public class GameIntercator {

    private final UserStore userStore;
    private final GameEndSteps gameEndSteps;
    private final NotTetrisElementInTrackSteps notTetrisElementInTrackSteps;
    private final Logger logger;
    private final BaseSteps baseSteps;


    public GameIntercator(UserStore userStore,
                          GameEndSteps gameEndSteps,
                          NotTetrisElementInTrackSteps notTetrisElementInTrackSteps,
                          BaseSteps baseSteps,
                          Logger logger) {
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
            baseSteps.execute(username);
            notTetrisElementInTrackSteps.execute(username);
            gameEndSteps.execute(username);
        } catch (CoreException e) {
            logger.debug("Core exception occurred while maintenance track: ", e);
        } catch (Exception e) {
            logger.error("Unexpected Exception occurred while maintenance track: ", e);
            throw new UnexpectedGameStopException(e);
        }
    }
}
