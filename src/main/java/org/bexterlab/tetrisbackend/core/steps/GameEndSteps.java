package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.gateway.GameStore;
import org.bexterlab.tetrisbackend.core.gateway.ScoreBoardStore;
import org.bexterlab.tetrisbackend.core.gateway.UserInformationStore;
import org.bexterlab.tetrisbackend.core.gateway.UserStore;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.Arrays;

public class GameEndSteps {

    private final GameStore gameStore;
    private final UserStore userStore;
    private final int deadRowIndex;
    private final ScoreBoardStore scoreBoardStore;
    private final UserInformationStore userInformationStore;

    public GameEndSteps(GameStore gameStore, UserStore userStore,
                        int deadRowIndex,
                        ScoreBoardStore scoreBoardStore,
                        UserInformationStore userInformationStore) {
        this.gameStore = gameStore;
        this.userStore = userStore;
        this.deadRowIndex = deadRowIndex;
        this.scoreBoardStore = scoreBoardStore;
        this.userInformationStore = userInformationStore;
    }

    public void execute(String username) {
        TrackElement[][] track = gameStore.findTrackByUser(username);
        if (isGameFinish(track)) {
            scoreBoardStore.addPlayerIntoScoreBoard(username, userStore.findPoint(username));
            gameStore.removeGame(username);
            userInformationStore.saveRound(username);
        }

    }

    private boolean isGameFinish(TrackElement[][] track) {
        return Arrays.stream(track[deadRowIndex]).anyMatch(y -> y == TrackElement.POINT);
    }
}
