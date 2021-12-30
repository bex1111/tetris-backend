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


    private GameStoreFake gameStoreFake;
    private GameEndSteps gameEndSteps;
    private String username;
    private UserStoreFake userStoreFake;

    @BeforeEach
    void setUp() {
        this.username = "test";
        this.gameStoreFake = new GameStoreFake();
        this.userStoreFake = new UserStoreFake();
        this.gameEndSteps = new GameEndSteps(gameStoreFake, userStoreFake, 0);
    }

    @Test
    void notFinishTest() {
        gameStoreFake.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{EMPTY}
        };
        gameEndSteps.execute(username);
        Assertions.assertEquals(username, gameStoreFake.findTrackByUserUsername);
        Assertions.assertNull(gameStoreFake.removeGameUsername);
        Assertions.assertNull(userStoreFake.addPlayerIntoScoreBoardUsername);
    }

    @Test
    void finishTest() {
        gameStoreFake.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{POINT}
        };
        gameEndSteps.execute(username);
        Assertions.assertEquals(username, gameStoreFake.findTrackByUserUsername);
        Assertions.assertEquals(username, gameStoreFake.removeGameUsername);
        Assertions.assertEquals(username, userStoreFake.addPlayerIntoScoreBoardUsername);
    }
}