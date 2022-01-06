package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.TetrisStepFactoryFake;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseStepsTest {


    GameStoreFake gameStore;
    TetrisStepFactoryFake tetrisStepFactory;
    BaseSteps baseSteps;
    private TrackElement[][] track;
    private String username;

    @BeforeEach
    void setUp() {
        this.track = new TrackElement[0][];
        this.username = "test";
        this.gameStore = new GameStoreFake();
        this.tetrisStepFactory = new TetrisStepFactoryFake();
        this.baseSteps = new BaseSteps(tetrisStepFactory, gameStore);
        this.gameStore.findTrackByUser = track;
    }

    @Test
    void notMoveTest() {
        gameStore.findNextMovement = null;
        baseSteps.execute(username);
        Assertions.assertEquals(username, gameStore.findTrackByUserUsername);
        Assertions.assertEquals(track, tetrisStepFactory.moveDownTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.collideElementTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.clearFullRowTrackElement);
        Assertions.assertEquals(username, gameStore.storeNewTrackUsername);
        Assertions.assertEquals(track, gameStore.storeNewTrack);

        Assertions.assertNull(tetrisStepFactory.rotateLeftTrackElement);
        Assertions.assertNull(tetrisStepFactory.rotateRightTrackElement);
        Assertions.assertNull(tetrisStepFactory.moveLeftTrackElement);
        Assertions.assertNull(tetrisStepFactory.moveRightTrackElement);
    }

    @Test
    void rotateLeftTest() {
        gameStore.findNextMovement = Movement.ROTATE_LEFT;
        baseSteps.execute(username);
        Assertions.assertEquals(username, gameStore.findTrackByUserUsername);
        Assertions.assertEquals(track, tetrisStepFactory.moveDownTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.collideElementTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.clearFullRowTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.rotateLeftTrackElement);
        Assertions.assertEquals(username, gameStore.storeNewTrackUsername);
        Assertions.assertEquals(track, gameStore.storeNewTrack);

        Assertions.assertNull(tetrisStepFactory.rotateRightTrackElement);
        Assertions.assertNull(tetrisStepFactory.moveLeftTrackElement);
        Assertions.assertNull(tetrisStepFactory.moveRightTrackElement);
    }

    @Test
    void rotateRightTest() {
        gameStore.findNextMovement = Movement.ROTATE_RIGHT;
        baseSteps.execute(username);
        Assertions.assertEquals(username, gameStore.findTrackByUserUsername);
        Assertions.assertEquals(track, tetrisStepFactory.moveDownTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.collideElementTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.clearFullRowTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.rotateRightTrackElement);
        Assertions.assertEquals(username, gameStore.storeNewTrackUsername);
        Assertions.assertEquals(track, gameStore.storeNewTrack);

        Assertions.assertNull(tetrisStepFactory.rotateLeftTrackElement);
        Assertions.assertNull(tetrisStepFactory.moveLeftTrackElement);
        Assertions.assertNull(tetrisStepFactory.moveRightTrackElement);
    }

    @Test
    void moveRightTest() {
        gameStore.findNextMovement = Movement.MOVE_RIGHT;
        baseSteps.execute(username);
        Assertions.assertEquals(username, gameStore.findTrackByUserUsername);
        Assertions.assertEquals(track, tetrisStepFactory.moveDownTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.collideElementTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.clearFullRowTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.moveRightTrackElement);
        Assertions.assertEquals(username, gameStore.storeNewTrackUsername);
        Assertions.assertEquals(track, gameStore.storeNewTrack);

        Assertions.assertNull(tetrisStepFactory.rotateLeftTrackElement);
        Assertions.assertNull(tetrisStepFactory.moveLeftTrackElement);
        Assertions.assertNull(tetrisStepFactory.rotateRightTrackElement);
    }

    @Test
    void moveLeftTest() {
        gameStore.findNextMovement = Movement.MOVE_LEFT;
        baseSteps.execute(username);
        Assertions.assertEquals(username, gameStore.findTrackByUserUsername);
        Assertions.assertEquals(track, tetrisStepFactory.moveDownTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.collideElementTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.clearFullRowTrackElement);
        Assertions.assertEquals(track, tetrisStepFactory.moveLeftTrackElement);
        Assertions.assertEquals(username, gameStore.storeNewTrackUsername);
        Assertions.assertEquals(track, gameStore.storeNewTrack);

        Assertions.assertNull(tetrisStepFactory.rotateLeftTrackElement);
        Assertions.assertNull(tetrisStepFactory.moveRightTrackElement);
        Assertions.assertNull(tetrisStepFactory.rotateRightTrackElement);
    }
}