package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.exception.CoreException;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.Movement;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class GameIntercator {

    private final GameStore gameStore;
    private final UserStore userStore;
    private final TetrisStepFactory tetrisStepFactory;
    private final Logger logger;
    private final int deadRowIndex;


    public GameIntercator(GameStore gameStore, UserStore userStore,
                          TetrisStepFactory tetrisStepFactory, Logger logger, int deadRowIndex) {
        this.gameStore = gameStore;
        this.userStore = userStore;
        this.tetrisStepFactory = tetrisStepFactory;
        this.logger = logger;
        this.deadRowIndex = deadRowIndex;
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
            userStore.storePoint(game, tetrisStepFactory.countPoints(track));
            track = tetrisStepFactory.spawnNewElement(track, game.tetrisElements().next());
            TetrisElement tetrisElement =
                    tetrisStepFactory.drawTetrisElement();
            game = gameStore.storeNewTetrisElement(game, tetrisElement);
        }
        if (isGameFinish(track)) {
            gameStore.removeGame(game);
            userStore.addPlayerIntoScoreBoard(game.user());
        }
        gameStore.storeNewTrack(game, track);
        logTrackForDevelop(game);
    }

    private boolean isGameFinish(TrackElement[][] track) {
        return Arrays.stream(track[deadRowIndex]).anyMatch(y -> y == TrackElement.POINT);
    }

    //Todo delete me
    private void logTrackForDevelop(Game game) {
        logger.info("\n" + "-".repeat(game.track().length) + "\n" +
                Arrays.stream(game.track())
                        .map(row -> Arrays.stream(row)
                                .map(column -> {
                                            switch (column) {
                                                case POINT -> {
                                                    return "P";
                                                }
                                                case EMPTY -> {
                                                    return " ";
                                                }
                                                default -> {
                                                    return "X";
                                                }
                                            }
                                        }
                                )
                                .collect(Collectors.joining(" ")))
                        .collect(Collectors.joining("|\n")) +
                "\n" + "-".repeat(game.track().length) + "\n");
    }

    private boolean isNotTetrisElementInTheTrack(TrackElement[][] track) {
        return Arrays.stream(track)
                .allMatch(row ->
                        Arrays.stream(row)
                                .noneMatch(column -> column.isNotFix));
    }

    //Fixme refact me
    private TrackElement[][] controlElement(ConcurrentLinkedQueue<Movement> movements, TrackElement[][] track) {
        if (!movements.isEmpty()) {
            switch (movements.poll()) {
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
                case null -> {
                    //Do nothing
                }
                default -> throw new AssertionError("Not implemented movement");
            }
        }
        return track;
    }


}
