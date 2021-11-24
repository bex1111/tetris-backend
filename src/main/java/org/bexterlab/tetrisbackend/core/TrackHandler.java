package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.exception.CoreException;
import org.bexterlab.tetrisbackend.core.maintenance.NewElementSpawner;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.Movement;
import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class TrackHandler {

    private final GameStore gameStore;
    private final TetrisStepFactory tetrisStepFactory;
    private final Logger logger;


    public TrackHandler(GameStore gameStore, TetrisStepFactory tetrisStepFactory, Logger logger) {
        this.gameStore = gameStore;
        this.tetrisStepFactory = tetrisStepFactory;
        this.logger = logger;
    }

    public void maintenanceTracks() {
        this.gameStore.getGames().forEach(this::maintenanceTrack);
    }

    private void maintenanceTrack(Game game) {
        try {
            doSteps(game);
        } catch (CoreException e) {
            logger.debug("Core exception occurred while maintenance track: ", e);
        } catch (Exception e) {
            logger.error("Exception occurred while maintenance track: ", e);
        }
    }

    private void doSteps(Game game) {
        TrackElement[][] track = game.track();
        track = controlElement(game.movementQueue(), track);
        track = tetrisStepFactory.moveDown(track);
        track = tetrisStepFactory.collideElement(track);
        track = tetrisStepFactory.clearFullRow(track);
        if (isNotTetrisElementInTheTrack(track)) {
            NewElementSpawner.TetrisElement tetrisElement =
                    tetrisStepFactory.drawTetrisElement();
            track = tetrisStepFactory
                    .spawnNewElement(track, game.tetrisElements().next());
            gameStore.storeNewTetrisElement(game, tetrisElement);
        }
        logger.info("\n-------------------\n" +
                Arrays.stream(game.track())
                        .map(x -> Arrays.stream(x)
                                .map(Enum::name)
                                .collect(Collectors.joining(" ")))
                        .collect(Collectors.joining("|\n")) +
                "\n-------------------\n");
    }

    private boolean isNotTetrisElementInTheTrack(TrackElement[][] track) {
        return Arrays.stream(track)
                .allMatch(row ->
                        Arrays.stream(row)
                                .noneMatch(column -> column.isNotFix));
    }

    private TrackElement[][] controlElement(LinkedList<Movement> movements, TrackElement[][] track) {
        if (!movements.isEmpty()) {
            switch (movements.pop()) {
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
        return track;
    }


}
