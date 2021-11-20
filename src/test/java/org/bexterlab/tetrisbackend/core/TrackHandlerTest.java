package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.TetrisStepFactoryFake;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.bexterlab.tetrisbackend.entity.Movement.*;

class TrackHandlerTest {


    private GameStoreFake gameStore;
    private TetrisStepFactoryFake tetrisStepFactoryFake;
    private TrackHandler trackHandler;

    @BeforeEach
    void setUp() {
        tetrisStepFactoryFake = new TetrisStepFactoryFake();
        gameStore = new GameStoreFake();
        trackHandler = new TrackHandler(gameStore, tetrisStepFactoryFake);
    }

    @Test
    void rotateLeftTest() {
        gameStore.game = createGame(ROTATE_LEFT);
        trackHandler.move();
        Assertions.assertEquals(List.of("rotateLeft",
                        "moveDown",
                        "collideElement",
                        "clearFullRow",
                        "drawNewElementIfNotExist"),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void rotateRightTest() {
        gameStore.game = createGame(ROTATE_RIGHT);
        trackHandler.move();
        Assertions.assertEquals(List.of("rotateRight",
                        "moveDown",
                        "collideElement",
                        "clearFullRow",
                        "drawNewElementIfNotExist"),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void moveRightTest() {
        gameStore.game = createGame(MOVE_RIGHT);
        trackHandler.move();
        Assertions.assertEquals(List.of("moveRight",
                        "moveDown",
                        "collideElement",
                        "clearFullRow",
                        "drawNewElementIfNotExist"),
                tetrisStepFactoryFake.steps);
    }

    @Test
    void moveLeftTest() {
        gameStore.game = createGame(MOVE_LEFT);
        trackHandler.move();
        Assertions.assertEquals(List.of("moveLeft",
                        "moveDown",
                        "collideElement",
                        "clearFullRow",
                        "drawNewElementIfNotExist"),
                tetrisStepFactoryFake.steps);
    }

    //FIXME more test

    private Game createGame(Movement movement) {
        return new Game(null, null, new LinkedList<>(List.of(movement)),
                null);
    }
}