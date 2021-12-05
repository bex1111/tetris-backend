package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.TetrisStepFactoryFake;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.Movement;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static org.bexterlab.tetrisbackend.core.maintenance.TetrisElement.LEFT_L;
import static org.bexterlab.tetrisbackend.core.maintenance.TetrisElement.LEFT_PYRAMID;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE;
import static org.bexterlab.tetrisbackend.entity.Movement.*;

class TrackHandlerTest {


    private GameStoreFake gameStore;
    private TetrisStepFactoryFake tetrisStepFactoryFake;
    private TrackHandler trackHandler;

    @BeforeEach
    void setUp() {
        tetrisStepFactoryFake = new TetrisStepFactoryFake();
        gameStore = new GameStoreFake();
        trackHandler = new TrackHandler(gameStore,
                tetrisStepFactoryFake,
                LoggerFactory.getLogger("Tetris logger"));
    }

    @Test
    void rotateLeftTest() {
        gameStore.game = createGame(ROTATE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE);
        trackHandler.maintenanceTracks();
        Assertions.assertEquals(List.of("rotateLeft",
                        "moveDown",
                        "collideElement",
                        "clearFullRow"
                ),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void rotateRightTest() {
        gameStore.game = createGame(ROTATE_RIGHT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE);
        trackHandler.maintenanceTracks();
        Assertions.assertEquals(List.of("rotateRight",
                        "moveDown",
                        "collideElement",
                        "clearFullRow"
                ),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void moveRightTest() {
        gameStore.game = createGame(MOVE_RIGHT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE);
        trackHandler.maintenanceTracks();
        Assertions.assertEquals(List.of("moveRight",
                        "moveDown",
                        "collideElement",
                        "clearFullRow"
                ),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void moveLeftTest() {
        gameStore.game = createGame(MOVE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE);
        trackHandler.maintenanceTracks();
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
        gameStore.game = createGame(MOVE_LEFT, EMPTY, TetrisElement.SQUARE, TetrisElement.LEFT_L);
        trackHandler.maintenanceTracks();
        Assertions.assertEquals(List.of("moveLeft",
                        "moveDown",
                        "collideElement",
                        "clearFullRow",
                        "spawnNewElement",
                        "drawTetrisElement"
                ),
                tetrisStepFactoryFake.steps);
        Assertions.assertEquals(tetrisStepFactoryFake.spawnNewTetrisElement, LEFT_L);
        Assertions.assertEquals(tetrisStepFactoryFake.draw, gameStore.tetrisNewElement);

    }

    private Game createGame(Movement movement, TrackElement trackElement) {
        return createGame(movement, trackElement, null, null);
    }

    private Game createGame(Movement movement, TrackElement trackElement, TetrisElement current, TetrisElement next) {
        return new Game(null,
                new TrackElement[][]{
                        new TrackElement[]{trackElement}}
                , new LinkedList<>(List.of(movement)),
                new TetrisElements(current, next));
    }
}