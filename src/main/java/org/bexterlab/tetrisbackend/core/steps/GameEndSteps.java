package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.gateway.*;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class GameEndSteps {

    private final GameStore gameStore;
    private final UserStore userStore;
    private final int deadRowIndex;
    private final int gameTimeInMinutes;
    private final ScoreBoardStore scoreBoardStore;
    private final UserInformationStore userInformationStore;
    private final Logger logger;

    public GameEndSteps(GameStore gameStore, UserStore userStore,
                        int deadRowIndex,
                        int gameTimeInMinutes, ScoreBoardStore scoreBoardStore,
                        UserInformationStore userInformationStore, Logger logger) {
        this.gameStore = gameStore;
        this.userStore = userStore;
        this.deadRowIndex = deadRowIndex;
        this.gameTimeInMinutes = gameTimeInMinutes;
        this.scoreBoardStore = scoreBoardStore;
        this.userInformationStore = userInformationStore;
        this.logger = logger;
    }

    public void execute(String username) {
        TrackElement[][] track = gameStore.findTrackByUser(username);
        if (isGameReachMaxGameTime(username) || isGameFinish(track)) {
            logger.info(
                    String.format("Game end username: %s points: %s duration: %S",
                            username,
                            userStore.findPoint(username),
                            formatDuration(Duration.between(gameStore.findStartTime(username), LocalDateTime.now()))));
            scoreBoardStore.addPlayerIntoScoreBoard(username, userStore.findPoint(username));
            gameStore.removeGame(username);
            userInformationStore.saveRound(username);
        }

    }

    private boolean isGameReachMaxGameTime(String username) {
        return gameTimeInMinutes <=
                ChronoUnit.MINUTES.between(gameStore.findStartTime(username),
                        LocalDateTime.now());
    }

    private boolean isGameFinish(TrackElement[][] track) {
        return Arrays.stream(track[deadRowIndex]).anyMatch(y -> y == TrackElement.POINT);
    }

    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String positive = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return seconds < 0 ? "-" + positive : positive;
    }

}
