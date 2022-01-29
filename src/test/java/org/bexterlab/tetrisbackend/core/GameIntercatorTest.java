package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.commonmock.LoggerSpy;
import org.bexterlab.tetrisbackend.core.mock.BaseStepsFake;
import org.bexterlab.tetrisbackend.core.mock.GameEndStepsFake;
import org.bexterlab.tetrisbackend.core.mock.NotTetrisElementInTrackStepsFake;
import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
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

    @BeforeEach
    void setUp() {
        steps = new ArrayList<>();
        userStoreFake = new UserStoreFake();
        gameIntercator = new GameIntercator(userStoreFake,
                new GameEndStepsFake(steps),
                new NotTetrisElementInTrackStepsFake(steps),
                new BaseStepsFake(steps),
                new LoggerSpy());
    }

    @Test
    void noCallTest() {
        userStoreFake.usernameList = Collections.emptyList();
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(Collections.emptyList(), steps);
    }

    @Test
    void twoTimesCallTest() {
        userStoreFake.usernameList = List.of("test1", "test2");
        gameIntercator.maintenanceTracks();
        Assertions.assertEquals(List.of("BaseStepsFake|execute",
                        "NotTetrisElementInTrackStepsFake|execute",
                        "GameEndStepsFake|execute",
                        "BaseStepsFake|execute",
                        "NotTetrisElementInTrackStepsFake|execute",
                        "GameEndStepsFake|execute"),
                steps);
    }
}