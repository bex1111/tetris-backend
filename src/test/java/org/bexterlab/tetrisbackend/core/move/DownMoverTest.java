package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class DownMoverTest {

    private TetrisStepFactory tetrisStepFactory;

    @BeforeEach
    void setUp() {
        tetrisStepFactory = new TetrisStepFactory();
    }

    @Test
    void moveDownPoint2x1Test() {
        TrackElement[][] actualTrack = tetrisStepFactory.moveDown(new TrackElement[][]{
                new TrackElement[]{POINT},
                new TrackElement[]{EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY},
                new TrackElement[]{POINT}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void moveDownPoint2x2Test() {
        TrackElement[][] actualTrack = tetrisStepFactory.moveDown(new TrackElement[][]{
                new TrackElement[]{POINT, POINT},
                new TrackElement[]{EMPTY, POINT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{POINT, POINT},
                new TrackElement[]{EMPTY, POINT}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void moveDownPoint4x2Test() {
        TrackElement[][] actualTrack = tetrisStepFactory.moveDown(new TrackElement[][]{
                new TrackElement[]{POINT, POINT},
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{EMPTY, POINT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{POINT, POINT},
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{EMPTY, POINT}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void moveDownElementTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.moveDown(new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{EMPTY, POINT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, POINT}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void moveDownElementAndPointTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.moveDown(new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_MIDDLE, POINT},
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{EMPTY, POINT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_MIDDLE, POINT},
                new TrackElement[]{EMPTY, POINT}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

}