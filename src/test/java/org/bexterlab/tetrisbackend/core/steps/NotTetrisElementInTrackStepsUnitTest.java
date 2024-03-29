package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.TetrisStepFactoryFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.THREE_LONG_ELEMENT_UP_MIDDLE;

class NotTetrisElementInTrackStepsUnitTest {

    private String username;
    private TetrisStepFactoryFake tetrisStepFactoryFake;
    private GameStoreFake gameStoreFake;
    private UserStoreFake userStoreFake;
    private NotTetrisElementInTrackSteps notTetrisElementInTrackSteps;
    private List<String> callMethodName;

    @BeforeEach
    void setUp() {
        this.callMethodName = new ArrayList<>();
        this.tetrisStepFactoryFake = new TetrisStepFactoryFake(callMethodName);
        this.gameStoreFake = new GameStoreFake(callMethodName);
        this.userStoreFake = new UserStoreFake(callMethodName);
        this.notTetrisElementInTrackSteps =
                new NotTetrisElementInTrackSteps(tetrisStepFactoryFake,
                        gameStoreFake,
                        userStoreFake);
        this.username = "test";
        tetrisStepFactoryFake.count = 5L;
        gameStoreFake.findNextTetrisElement = TetrisElement.STRAIGHT;
    }

    @Test
    void spawnNewTetrisElement() {
        gameStoreFake.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{EMPTY}
        };
        notTetrisElementInTrackSteps.execute(username);

        Assertions.assertEquals(username, gameStoreFake.findTrackByUserUsername);
        Assertions.assertEquals(username, userStoreFake.storePointUserName);
        Assertions.assertEquals(username, gameStoreFake.findNextTetrisElementUsername);
        Assertions.assertEquals(username, gameStoreFake.storeNewTrackUsername);
        Assertions.assertEquals(username, gameStoreFake.storeNewTetrisElementUsername);

        Assertions.assertEquals(gameStoreFake.findTrackByUser, tetrisStepFactoryFake.countPointsTrackElement);
        Assertions.assertEquals(gameStoreFake.findTrackByUser, tetrisStepFactoryFake.spawnNewElementTrackElement);
        Assertions.assertEquals(gameStoreFake.findTrackByUser, gameStoreFake.storeNewTrack);

        Assertions.assertEquals(tetrisStepFactoryFake.count, userStoreFake.point);
        Assertions.assertEquals(gameStoreFake.findNextTetrisElement, tetrisStepFactoryFake.spawnNewTetrisElement);
        Assertions.assertEquals(tetrisStepFactoryFake.draw, gameStoreFake.storeNewTetrisElement);

        final List<String> callMethodOrder = List.of("findTrackByUser",
                "countPoints",
                "storePoint",
                "findNextTetrisElement",
                "spawnNewElement",
                "storeNewTrack",
                "drawTetrisElement",
                "storeNewTetrisElement");
        Assertions.assertEquals(callMethodOrder, callMethodName);
    }

    @Test
    void notSpawnNewTetrisElement() {
        gameStoreFake.findTrackByUser = new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_MIDDLE}
        };
        notTetrisElementInTrackSteps.execute(username);
        Assertions.assertEquals(username, gameStoreFake.findTrackByUserUsername);
        Assertions.assertNull(userStoreFake.storePointUserName);
        Assertions.assertNull(gameStoreFake.findNextTetrisElementUsername);
        Assertions.assertNull(gameStoreFake.storeNewTrackUsername);
        Assertions.assertNull(gameStoreFake.storeNewTetrisElementUsername);

        Assertions.assertNull(tetrisStepFactoryFake.countPointsTrackElement);
        Assertions.assertNull(tetrisStepFactoryFake.spawnNewElementTrackElement);
        Assertions.assertNull(gameStoreFake.storeNewTrack);

        Assertions.assertNull(userStoreFake.point);
        Assertions.assertNull(tetrisStepFactoryFake.spawnNewTetrisElement);
        Assertions.assertNull(gameStoreFake.storeNewTetrisElement);

        final List<String> callMethodOrder = List.of("findTrackByUser");
        Assertions.assertEquals(callMethodOrder, callMethodName);
    }
}