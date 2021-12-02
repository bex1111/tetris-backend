package org.bexterlab.tetrisbackend.core.maintenance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class TetrisElementLotteryTest {

    @Test
    void drawAllTest() {
        int capacity = 100;
        List<TetrisElement> tetrisElements = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            tetrisElements.add(new TetrisElementLottery().draw());
        }
        Assertions.assertEquals(TetrisElement.values().length, new HashSet<>(tetrisElements).size());
    }


}