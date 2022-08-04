package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.core.maintenance.TetrisElement.*;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class NewElementSpawnerUnitTest {

    private TetrisStepFactory tetrisStepFactory;

    @BeforeEach
    void setUp() {
        tetrisStepFactory = new TetrisStepFactory();
    }

    @Test
    void spawnNewSquareTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.spawnNewElement(SQUARE, new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementRightLTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.spawnNewElement(RIGHT_L, new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementLeftLTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.spawnNewElement(LEFT_L, new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_LEFT, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementRightPyramidTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.spawnNewElement(RIGHT_PYRAMID, new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementLeftPyramidTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.spawnNewElement(LEFT_PYRAMID, new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementStraightTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.spawnNewElement(STRAIGHT, new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }


}