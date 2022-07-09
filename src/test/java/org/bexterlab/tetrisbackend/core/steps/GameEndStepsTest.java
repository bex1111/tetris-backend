package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.ScoreBoardStoreFake;
import org.bexterlab.tetrisbackend.core.mock.UserInformationStoreFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

class GameEndStepsTest {

    private GameStoreFake gameStoreFake;
    private GameEndSteps gameEndSteps;
    private String username;
    private ScoreBoardStoreFake scoreBoardStoreFake;
    private UserStoreFake userStoreFake;
    private UserInformationStoreFake userInformationStoreFake;
    public List<String> callMethodName;

    @BeforeEach
    void setUp() {
        this.username = "test";
        this.callMethodName = new ArrayList<>();
        this.gameStoreFake = new GameStoreFake(callMethodName);
        this.scoreBoardStoreFake = new ScoreBoardStoreFake(callMethodName);
        this.userStoreFake = new UserStoreFake(callMethodName);
        this.userInformationStoreFake = new UserInformationStoreFake(callMethodName);
        this.gameEndSteps = new GameEndSteps(gameStoreFake,
                userStoreFake,
                0,
                scoreBoardStoreFake,
                userInformationStoreFake);
    }

    @Test
    void notFinishTest() {
        gameStoreFake.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{EMPTY}
        };
        gameEndSteps.execute(username);

        Assertions.assertEquals(username, gameStoreFake.findTrackByUserUsername);
        Assertions.assertNull(userStoreFake.findPointUsername);
        Assertions.assertNull(scoreBoardStoreFake.addPlayerIntoScoreBoardUsername);
        Assertions.assertNull(gameStoreFake.removeGameUsername);
        Assertions.assertNull(userInformationStoreFake.saveRoundUsername);

        final List<String> callMethodOrder = List.of("findTrackByUser");
        Assertions.assertEquals(callMethodOrder, callMethodName);
    }

    @Test
    void finishTest() {
        gameStoreFake.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{POINT}
        };
        gameEndSteps.execute(username);

        Assertions.assertEquals(username, gameStoreFake.findTrackByUserUsername);
        Assertions.assertEquals(username, gameStoreFake.removeGameUsername);
        Assertions.assertEquals(username, scoreBoardStoreFake.addPlayerIntoScoreBoardUsername);
        Assertions.assertEquals(username, userStoreFake.findPointUsername);
        Assertions.assertEquals(username, userInformationStoreFake.saveRoundUsername);

        final List<String> callMethodOrder = List.of("findTrackByUser",
                "findPoint",
                "addPlayerIntoScoreBoard",
                "removeGame",
                "saveRound");
        Assertions.assertEquals(callMethodOrder, callMethodName);
    }
}