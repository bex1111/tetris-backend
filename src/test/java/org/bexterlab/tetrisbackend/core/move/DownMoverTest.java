package org.bexterlab.tetrisbackend.core.move;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class DownMoverTest {

    private DownMover downMover;

    @BeforeEach
    void setUp() {
        downMover = new DownMover();
    }

    @Test
    void moveDownTest() {
        TrackElement[][] actualTrack = downMover.moveDown(new TrackElement[][]{
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
    void moveDown2Test() {
        TrackElement[][] actualTrack = downMover.moveDown(new TrackElement[][]{
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
    void moveDown3Test() {
        TrackElement[][] actualTrack = downMover.moveDown(new TrackElement[][]{
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
    void moveDown4Test() {
        TrackElement[][] actualTrack = downMover.moveDown(new TrackElement[][]{
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

}