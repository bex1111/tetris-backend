package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.gateway.GameStore;
import org.bexterlab.tetrisbackend.core.gateway.UserStore;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.Arrays;

public class NotTetrisElementInTrackSteps {

    private final TetrisStepFactory tetrisStepFactory;
    private final GameStore gameStore;
    private final UserStore userStore;

    public NotTetrisElementInTrackSteps(TetrisStepFactory tetrisStepFactory,
                                        GameStore gameStore, UserStore userStore) {
        this.tetrisStepFactory = tetrisStepFactory;
        this.gameStore = gameStore;
        this.userStore = userStore;
    }

    public void execute(String username) {
        TrackElement[][] track = gameStore.findTrackByUser(username);
        if (isNotTetrisElementInTheTrack(track)) {
            userStore.storePoint(username, tetrisStepFactory.countPoints(track));
            track = tetrisStepFactory.spawnNewElement(gameStore.findNextTetrisElement(username), track);
            gameStore.storeNewTrack(username, track);
            gameStore.storeNewTetrisElement(username, tetrisStepFactory.drawTetrisElement());
        }
    }

    private boolean isNotTetrisElementInTheTrack(TrackElement[][] track) {
        return Arrays.stream(track)
                .allMatch(row ->
                        Arrays.stream(row)
                                .noneMatch(column -> column.isNotFix));
    }
}
