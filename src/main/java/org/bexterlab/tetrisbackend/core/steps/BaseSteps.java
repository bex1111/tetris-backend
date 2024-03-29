package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.exception.CannotMoveException;
import org.bexterlab.tetrisbackend.core.exception.CannotRotateException;
import org.bexterlab.tetrisbackend.core.gateway.GameStore;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import static org.bexterlab.tetrisbackend.core.move.Movement.*;

public class BaseSteps {

    private final TetrisStepFactory tetrisStepFactory;
    private final GameStore gameStore;

    public BaseSteps(TetrisStepFactory tetrisStepFactory,
                     GameStore gameStore) {
        this.tetrisStepFactory = tetrisStepFactory;
        this.gameStore = gameStore;
    }

    public void execute(String username) {
        TrackElement[][] track = gameStore.findTrackByUser(username);
        track = controlElement(track, gameStore.findNextMovement(username));
        track = tetrisStepFactory.collideElement(track);
        track = tetrisStepFactory.moveDown(track);
        track = tetrisStepFactory.clearFullRow(track);
        gameStore.storeNewTrack(username, track);
    }

    private TrackElement[][] controlElement(TrackElement[][] track, Movement movement) {
        try {
            if (ROTATE_RIGHT == movement) {
                return tetrisStepFactory.rotateRight(track);
            }
            if (ROTATE_LEFT == movement) {
                return tetrisStepFactory.rotateLeft(track);
            }
            if (MOVE_RIGHT == movement) {
                return tetrisStepFactory.moveRight(track);
            }
            if (MOVE_LEFT == movement) {
                return tetrisStepFactory.moveLeft(track);
            }
        } catch (CannotRotateException | CannotMoveException e) {
            //ignore
        }
        return track;
    }
}
