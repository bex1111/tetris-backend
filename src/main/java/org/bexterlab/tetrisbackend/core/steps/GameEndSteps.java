package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.UserStore;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GameEndSteps {

    private final GameStore gameStore;
    private final UserStore userStore;
    private final int deadRowIndex;

    public GameEndSteps(GameStore gameStore, UserStore userStore, int deadRowIndex) {
        this.gameStore = gameStore;
        this.userStore = userStore;
        this.deadRowIndex = deadRowIndex;
    }

    public void execute(String username) {
        TrackElement[][] track = gameStore.findTrackByUser(username);
        if (isGameFinish(track)) {
            gameStore.removeGame(username);
            userStore.addPlayerIntoScoreBoard(username);
        }

    }

    private boolean isGameFinish(TrackElement[][] track) {
        return Arrays.stream(track[deadRowIndex]).anyMatch(y -> y == TrackElement.POINT);
    }

    //Todo delete me
    private void logTrackForDevelop(TrackElement[][] track) {
        logger.info("\n" + "-".repeat(track.length) + "\n" +
                Arrays.stream(track)
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
                "\n" + "-".repeat(track.length) + "\n");
    }
}
