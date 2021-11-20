package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.TetrisStepFactoryFake;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.Movement;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static org.bexterlab.tetrisbackend.entity.Movement.*;
import static org.bexterlab.tetrisbackend.entity.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.entity.TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE;

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
        gameStore.game = createGame(MOVE_LEFT, EMPTY);
        trackHandler.maintenanceTracks();
        Assertions.assertEquals(List.of("moveLeft",
                        "moveDown",
                        "collideElement",
                        "clearFullRow",
                        "drawTetrisElement",
                        "spawnNewElement"
                ),
                tetrisStepFactoryFake.steps);
    }

    private Game createGame(Movement movement, TrackElement trackElement) {
        return new Game(null,
                new TrackElement[][]{
                        new TrackElement[]{trackElement}}
                , new LinkedList<>(List.of(movement)),
                new TetrisElements(null, null));
    }
}