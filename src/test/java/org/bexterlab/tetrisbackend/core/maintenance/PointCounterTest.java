package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class PointCounterTest {

    @Test
    void countTest() {
        Assertions.assertEquals(4, new PointCounter().count(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY}}));

        Assertions.assertEquals(3, new PointCounter().count(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE},
                new TrackElement[]{EMPTY, EMPTY}}));

        Assertions.assertEquals(0, new PointCounter().count(new TrackElement[][]{
                new TrackElement[]{POINT, THREE_LONG_ELEMENT_UP_MIDDLE},
                new TrackElement[]{POINT, POINT}}));
    }

}