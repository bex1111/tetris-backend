package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.TetrisStepFactoryFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.Movement;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.bexterlab.tetrisbackend.core.maintenance.TetrisElement.LEFT_L;
import static org.bexterlab.tetrisbackend.core.maintenance.TetrisElement.LEFT_PYRAMID;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.entity.Movement.*;

class GameIntercatorTest {


    private GameStoreFake gameStoreFake;
    private UserStoreFake userStoreFake;
    private TetrisStepFactoryFake tetrisStepFactoryFake;
    private GameIntercator gameIntercator;

    @BeforeEach
    void setUp() {
        tetrisStepFactoryFake = new TetrisStepFactoryFake();
        gameStoreFake = new GameStoreFake();
        userStoreFake = new UserStoreFake();
        gameIntercator = new GameIntercator(gameStoreFake,
                userStoreFake, tetrisStepFactoryFake,
                LoggerFactory.getLogger("Tetris logger"));
    }

    @Test
    void rotateLeftTest() {
        gameStoreFake.game = createGame(ROTATE_LEFT);
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(List.of("rotateLeft",
                        "moveDown",
                        "collideElement",
                        "clearFullRow"
                ),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void rotateRightTest() {
        gameStoreFake.game = createGame(ROTATE_RIGHT);
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(List.of("rotateRight",
                        "moveDown",
                        "collideElement",
                        "clearFullRow"
                ),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void moveRightTest() {
        gameStoreFake.game = createGame(MOVE_RIGHT);
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(List.of("moveRight",
                        "moveDown",
                        "collideElement",
                        "clearFullRow"
                ),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void moveLeftTest() {
        gameStoreFake.game = createGame(MOVE_LEFT);
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(List.of("moveLeft",
                        "moveDown",
                        "collideElement",
                        "clearFullRow"
                ),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void newElementSpawnTest() {
        tetrisStepFactoryFake.draw = LEFT_PYRAMID;
        tetrisStepFactoryFake.count = 10L;
        gameStoreFake.game = createGame(MOVE_LEFT, EMPTY, TetrisElement.SQUARE, TetrisElement.LEFT_L);
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(List.of("moveLeft",
                        "moveDown",
                        "collideElement",
                        "clearFullRow",
                        "spawnNewElement",
                        "drawTetrisElement",
                        "countPoints"
                ),
                tetrisStepFactoryFake.steps);
        Assertions.assertEquals(tetrisStepFactoryFake.spawnNewTetrisElement, LEFT_L);
        Assertions.assertEquals(tetrisStepFactoryFake.draw, gameStoreFake.tetrisNewElement);
        Assertions.assertEquals(userStoreFake.game, gameStoreFake.game);
        Assertions.assertEquals(tetrisStepFactoryFake.count, userStoreFake.point);

    }

    private Game createGame(Movement movement) {
        return createGame(movement, TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE, null, null);
    }

    private Game createGame(Movement movement, TrackElement trackElement, TetrisElement current, TetrisElement next) {
        return new Game(null,
                new TrackElement[][]{
                        new TrackElement[]{trackElement}}
                , new ConcurrentLinkedQueue<>(List.of(movement)),
                new TetrisElements(current, next));
    }
}