package org.bexterlab.tetrisbackend.core.steps;

import org.bexterlab.tetrisbackend.core.mock.GameStoreFake;
import org.bexterlab.tetrisbackend.core.mock.TetrisStepFactoryFake;
import org.junit.jupiter.api.Test;

class BaseStepsTest {

    @Test
    void test() {
        BaseSteps baseSteps = new BaseSteps(new TetrisStepFactoryFake(), new GameStoreFake());
        baseSteps.execute("test");
    }
}