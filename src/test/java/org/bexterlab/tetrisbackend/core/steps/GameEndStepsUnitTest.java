package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.ScoreBoardStoreFake;
import org.bexterlab.tetrisbackend.core.mock.UserInformationStoreFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

class GameEndStepsUnitTest {

    private static final int GAME_TIME_IN_MINUTES = 5;
    private GameStoreFake gameStoreFake;
    private GameEndSteps gameEndSteps;
    private String username;
    private ScoreBoardStoreFake scoreBoardStoreFake;
    private UserStoreFake userStoreFake;
    private UserInformationStoreFake userInformationStoreFake;
    private List<String> callMethodName;
    private LoggerSpy logger;

    @BeforeEach
    void setUp() {
        this.username = "test";
        this.callMethodName = new ArrayList<>();
        this.gameStoreFake = new GameStoreFake(callMethodName);
        this.scoreBoardStoreFake = new ScoreBoardStoreFake(callMethodName);
        this.userStoreFake = new UserStoreFake(callMethodName);
        this.userInformationStoreFake = new UserInformationStoreFake(callMethodName);
        this.logger = new LoggerSpy();
        this.gameEndSteps = new GameEndSteps(gameStoreFake,
                userStoreFake,
                0,
                GAME_TIME_IN_MINUTES, scoreBoardStoreFake,
                userInformationStoreFake, logger);
    }

    @Test
    void notFinishTest() {
        gameStoreFake.startTime = LocalDateTime.now().minusMinutes(GAME_TIME_IN_MINUTES - 1);
        ;
        gameStoreFake.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{EMPTY}
        };
        gameEndSteps.execute(username);

        Assertions.assertEquals(username, gameStoreFake.findTrackByUserUsername);
        Assertions.assertNull(userStoreFake.findPointUsername);
        Assertions.assertNull(scoreBoardStoreFake.addPlayerIntoScoreBoardUsername);
        Assertions.assertNull(gameStoreFake.removeGameUsername);
        Assertions.assertNull(userInformationStoreFake.saveRoundUsername);

        final List<String> callMethodOrder = List.of("findTrackByUser", "findStartTime");
        Assertions.assertEquals(callMethodOrder, callMethodName);
        Assertions.assertNull(logger.object);
    }

    @Test
    void gameFinishTest() {
        gameStoreFake.startTime = LocalDateTime.now().minusMinutes(GAME_TIME_IN_MINUTES - 1);
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
                "findStartTime",
                "findPoint",
                "findStartTime",
                "findPoint",
                "addPlayerIntoScoreBoard",
                "removeGame",
                "saveRound");
        Assertions.assertEquals(callMethodOrder, callMethodName);
        Assertions.assertEquals("Game end username: test points: 1 duration: 0:04:00", logger.object);
    }

    @Test
    void maxGameTimeTest() {
        gameStoreFake.startTime = LocalDateTime.now().minusMinutes(GAME_TIME_IN_MINUTES);
        gameStoreFake.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{EMPTY}
        };
        gameEndSteps.execute(username);

        Assertions.assertEquals(username, gameStoreFake.findTrackByUserUsername);
        Assertions.assertEquals(username, gameStoreFake.findStartTimeUserName);
        Assertions.assertEquals(username, gameStoreFake.removeGameUsername);
        Assertions.assertEquals(username, scoreBoardStoreFake.addPlayerIntoScoreBoardUsername);
        Assertions.assertEquals(username, userStoreFake.findPointUsername);
        Assertions.assertEquals(username, userInformationStoreFake.saveRoundUsername);

        final List<String> callMethodOrder = List.of("findTrackByUser",
                "findStartTime",
                "findPoint",
                "findStartTime",
                "findPoint",
                "addPlayerIntoScoreBoard",
                "removeGame",
                "saveRound");
        Assertions.assertEquals(callMethodOrder, callMethodName);
        Assertions.assertEquals("Game end username: test points: 1 duration: 0:05:00", logger.object);
    }
}