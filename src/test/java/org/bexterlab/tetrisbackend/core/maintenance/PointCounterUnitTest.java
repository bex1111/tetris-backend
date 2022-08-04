package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class PointCounterUnitTest {

    private TetrisStepFactory tetrisStepFactory;

    @BeforeEach
    void setUp() {
        tetrisStepFactory = new TetrisStepFactory();
    }

    @Test
    void countEmptyTrackTest() {
        Assertions.assertEquals(4, tetrisStepFactory.countPoints(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY}}));
    }

    @Test
    void countWithOneElementInTheTrackTest() {
        Assertions.assertEquals(3, tetrisStepFactory.countPoints(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE},
                new TrackElement[]{EMPTY, EMPTY}}));
    }

    @Test
    void countWithFullTrackTest() {
        Assertions.assertEquals(0, tetrisStepFactory.countPoints(new TrackElement[][]{
                new TrackElement[]{POINT, THREE_LONG_ELEMENT_UP_MIDDLE},
                new TrackElement[]{POINT, POINT}}));
    }
}