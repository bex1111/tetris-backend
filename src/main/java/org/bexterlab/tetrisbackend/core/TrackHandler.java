package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.exception.CoreException;
import org.bexterlab.tetrisbackend.core.maintenance.NewElementSpawner;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.Movement;
import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.util.Optional;

public class TrackHandler {

    private final GameStore gameStore;
    private final TetrisStepFactory tetrisStepFactory;


    public TrackHandler(GameStore gameStore, TetrisStepFactory tetrisStepFactory) {
        this.gameStore = gameStore;
        this.tetrisStepFactory = tetrisStepFactory;
    }

    public void move() {
        this.gameStore.getGames().forEach(this::moveTrack);
    }

    private void moveTrack(Game game) {
        try {
            TrackElement[][] track = game.track();
            track = controlElement(game.movementQueue().pop(), track);
            track = tetrisStepFactory.moveDown(track);
            track = tetrisStepFactory.collideElement(track);
            track = tetrisStepFactory.clearFullRow(track);
            Optional<NewElementSpawner.TetrisElement> tetrisElement =
                    tetrisStepFactory.drawNewElementIfNotExist(track);
            if (tetrisElement.isPresent()) {
                track = tetrisStepFactory
                        .spawnNewElement(track, game.tetrisElements().next());
                gameStore.storeNewTetrisElement(game, tetrisElement.get());
            }
        } catch (CoreException e) {
            //do nothing
        }
    }

    private TrackElement[][] controlElement(Movement movement, TrackElement[][] track) {
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
                throw new AssertionError("Not implemented movement");
            }
        }
    }
}
