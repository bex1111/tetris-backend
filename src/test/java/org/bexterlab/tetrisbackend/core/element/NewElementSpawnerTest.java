package org.bexterlab.tetrisbackend.core.element;

import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.core.element.NewElementSpawner.TetrisElement.*;
import static org.bexterlab.tetrisbackend.entity.TrackElement.*;

class NewElementSpawnerTest {

    private NewElementSpawner newElementSpawner;

    @BeforeEach
    void setUp() {
        newElementSpawner = new NewElementSpawner();
    }

    @Test
    void spawnNewSquareTest() {
        TrackElement[][] actualTrack = newElementSpawner.swapNewIfNotExist(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        }, SQUARE);
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
        TrackElement[][] actualTrack = newElementSpawner.swapNewIfNotExist(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        }, RIGHT_L);
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementLeftLTest() {
        TrackElement[][] actualTrack = newElementSpawner.swapNewIfNotExist(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        }, LEFT_L);
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_LEFT, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementRightPyramidTest() {
        TrackElement[][] actualTrack = newElementSpawner.swapNewIfNotExist(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        }, RIGHT_PYRAMID);
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementLeftPyramidTest() {
        TrackElement[][] actualTrack = newElementSpawner.swapNewIfNotExist(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        }, LEFT_PYRAMID);
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void spawnNewElementStraightTest() {
        TrackElement[][] actualTrack = newElementSpawner.swapNewIfNotExist(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        }, STRAIGHT);
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }


}