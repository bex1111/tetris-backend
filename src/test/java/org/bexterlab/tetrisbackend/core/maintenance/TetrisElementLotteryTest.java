package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.bexterlab.tetrisbackend.entity.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.entity.TrackElement.values;

class TetrisElementLotteryTest {

    @Test
    void drawTest() {
        Arrays.stream(values()).filter(x -> !x.isNotFix).collect(Collectors.toList())
                .forEach(x ->
                        Assertions.assertTrue(
                                new TetrisElementLottery()
                                        .drawIfNotElementInTheTrack(new TrackElement[][]{
                                                new TrackElement[]{x}
                                        }).isPresent()));
    }

    @Test
    void notDrawTest() {
        Arrays.stream(values()).filter(x -> x.isNotFix).collect(Collectors.toList())
                .forEach(x ->
                        Assertions.assertTrue(
                                new TetrisElementLottery()
                                        .drawIfNotElementInTheTrack(new TrackElement[][]{
                                                new TrackElement[]{x}
                                        }).isEmpty()));
    }

    @Test
    void drawIfNotExistAllTest() {
        int capacity = 100;
        List<NewElementSpawner.TetrisElement> tetrisElements = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            tetrisElements.add(new TetrisElementLottery().drawIfNotElementInTheTrack(new TrackElement[][]{
                    new TrackElement[]{EMPTY}
            }).get());
        }
        Assertions.assertEquals(NewElementSpawner.TetrisElement.values().length, new HashSet<>(tetrisElements).size());
    }

    @Test
    void drawAllTest() {
        int capacity = 100;
        List<NewElementSpawner.TetrisElement> tetrisElements = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            tetrisElements.add(new TetrisElementLottery().draw());
        }
        Assertions.assertEquals(NewElementSpawner.TetrisElement.values().length, new HashSet<>(tetrisElements).size());
    }


}