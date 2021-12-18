package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

public class BaseSteps {

    private final TetrisStepFactory tetrisStepFactory;
    private final GameStore gameStore;

    public BaseSteps(TetrisStepFactory tetrisStepFactory, GameStore gameStore) {
        this.tetrisStepFactory = tetrisStepFactory;
        this.gameStore = gameStore;
    }

    public void execute(String username) {
        TrackElement[][] track = gameStore.findTrackByUser(username);
        track = controlElement(track, gameStore.findNextMovement(username));
        track = tetrisStepFactory.moveDown(track);
        track = tetrisStepFactory.collideElement(track);
        track = tetrisStepFactory.clearFullRow(track);
        gameStore.storeNewTrack(username, track);
    }

    private TrackElement[][] controlElement(TrackElement[][] track, Movement movement) {
        switch (movement) {
            case ROTATE_RIGHT -> {
                return tetrisStepFactory.rotateRight(track);
            }
            case ROTATE_LEFT -> {
                return tetrisStepFactory.rotateLeft(track);
            }
            case MOVE_RIGHT -> {
                return tetrisStepFactory.moveRight(track);
            }
            case MOVE_LEFT -> {
                return tetrisStepFactory.moveLeft(track);
            }
            default -> {
                return track;
            }
        }
    }
}
