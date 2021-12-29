package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

class GameEndStepsTest {


    private GameStoreFake gameStore;
    private GameEndSteps gameEndSteps;
    private String username;
    private UserStoreFake userStore;

    @BeforeEach
    void setUp() {
        this.username = "test";
        this.gameStore = new GameStoreFake();
        this.userStore = new UserStoreFake();
        this.gameEndSteps = new GameEndSteps(gameStore, userStore, 0);
    }

    @Test
    void notFinishTest() {
        gameStore.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{EMPTY}
        };
        gameEndSteps.execute(username);
        Assertions.assertEquals(username, gameStore.findTrackByUserUsername);
        Assertions.assertNull(gameStore.removeGameUsername);
        Assertions.assertNull(userStore.addPlayerIntoScoreBoardUsername);
    }

    @Test
    void finishTest() {
        gameStore.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{POINT}
        };
        gameEndSteps.execute(username);
        Assertions.assertEquals(username, gameStore.findTrackByUserUsername);
        Assertions.assertEquals(username, gameStore.removeGameUsername);
        Assertions.assertEquals(username, userStore.addPlayerIntoScoreBoardUsername);
    }
}