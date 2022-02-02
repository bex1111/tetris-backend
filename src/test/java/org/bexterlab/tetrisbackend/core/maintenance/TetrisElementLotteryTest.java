package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class TetrisElementLotteryTest {

    @Test
    void drawAllTest() {
        TetrisStepFactory tetrisStepFactory = new TetrisStepFactory();
        int capacity = 100;
        List<TetrisElement> tetrisElements = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            tetrisElements.add(tetrisStepFactory.drawTetrisElement());
        }
        Assertions.assertEquals(TetrisElement.values().length, new HashSet<>(tetrisElements).size());
    }


}