package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.UserStore;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.Arrays;

public class NotTetrisElementInTrackSteps {

    private final TetrisStepFactory tetrisStepFactory;
    private final GameStore gameStore;
    private final UserStore userStore;

    //FIXME test me
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
            track = tetrisStepFactory.spawnNewElement(track, gameStore.findNextTetrisElement(username));
            gameStore.storeNewTrack(username, track);
            TetrisElement tetrisElement =
                    tetrisStepFactory.drawTetrisElement();
            gameStore.storeNewTetrisElement(username, tetrisElement);
        }
    }

    private boolean isNotTetrisElementInTheTrack(TrackElement[][] track) {
        return Arrays.stream(track)
                .allMatch(row ->
                        Arrays.stream(row)
                                .noneMatch(column -> column.isNotFix));
    }
}
