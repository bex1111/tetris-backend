package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.bexterlab.tetrisbackend.core.exception.CanNotMoveException;
import org.bexterlab.tetrisbackend.core.mock.BaseStepsFake;
import org.bexterlab.tetrisbackend.core.mock.GameEndStepsFake;
import org.bexterlab.tetrisbackend.core.mock.NotTetrisElementInTrackStepsFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.exception.UnexpectedGameStopException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GameIntercatorTest {

    private UserStoreFake userStoreFake;
    private GameIntercator gameIntercator;
    private List<String> steps;
    private LoggerSpy loggerSpy;
    private GameEndStepsFake gameEndSteps;

    @BeforeEach
    void setUp() {
        steps = new ArrayList<>();
        userStoreFake = new UserStoreFake();
        loggerSpy = new LoggerSpy();
        gameEndSteps = new GameEndStepsFake(steps);
        gameIntercator = new GameIntercator(userStoreFake,
                gameEndSteps,
                new NotTetrisElementInTrackStepsFake(steps),
                new BaseStepsFake(steps),
                loggerSpy);
        userStoreFake.usernameList = List.of("test1", "test2");
    }

    @Test
    void noCallTest() {
        userStoreFake.usernameList = Collections.emptyList();
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(Collections.emptyList(), steps);
    }

    @Test
    void twoTimesCallTest() {
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(List.of("BaseStepsFake|execute",
                        "NotTetrisElementInTrackStepsFake|execute",
                        "GameEndStepsFake|execute",
                        "BaseStepsFake|execute",
                        "NotTetrisElementInTrackStepsFake|execute",
                        "GameEndStepsFake|execute"),
                steps);
    }

    @Test
    void expectedExceptionTest() {
        gameEndSteps.runtimeException = new CanNotMoveException();
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(List.of("BaseStepsFake|execute",
                        "NotTetrisElementInTrackStepsFake|execute",
                        "GameEndStepsFake|execute",
                        "BaseStepsFake|execute",
                        "NotTetrisElementInTrackStepsFake|execute",
                        "GameEndStepsFake|execute"),
                steps);
        Assertions.assertEquals("Core exception occurred while maintenance track: ", loggerSpy.object);
        Assertions.assertEquals(gameEndSteps.runtimeException, loggerSpy.throwable);
    }

    @Test
    void notExpectedExceptionTest() {
        gameEndSteps.runtimeException = new IllegalArgumentException();
        Assertions.assertThrows(UnexpectedGameStopException.class, () -> gameIntercator.maintenanceTracks());
        Assertions.assertEquals(List.of("BaseStepsFake|execute",
                        "NotTetrisElementInTrackStepsFake|execute",
                        "GameEndStepsFake|execute"),
                steps);
        Assertions.assertEquals("Unexpected Exception occurred while maintenance track: ", loggerSpy.object);
        Assertions.assertEquals(gameEndSteps.runtimeException, loggerSpy.throwable);
    }
}