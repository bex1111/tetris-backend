package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.exception.CanNotMoveException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class RightMoverTest {

    RightMover rightMover;

    @BeforeEach
    void setUp() {
        rightMover = new RightMover();
    }

    @Test
    void moveRight() {
        TrackElement[][] actualTrack = rightMover.moveRight(new TrackElement[][]{
                new TrackElement[]{SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT},
                new TrackElement[]{POINT, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void moveRightNoSpaceTest() {
        TrackElement[][] actualTrack = new TrackElement[][]{
                new TrackElement[]{SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT},
                new TrackElement[]{POINT, EMPTY}
        };

        Assertions.assertThrows(CanNotMoveException.class, () -> rightMover.moveRight(actualTrack));
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT},
                new TrackElement[]{POINT, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void moveRightCollideTest() {
        TrackElement[][] actualTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT},
                new TrackElement[]{POINT, SQUARE_POINT},
                new TrackElement[]{POINT, EMPTY}
        };

        Assertions.assertThrows(CanNotMoveException.class, () -> rightMover.moveRight(actualTrack));
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT},
                new TrackElement[]{POINT, SQUARE_POINT},
                new TrackElement[]{POINT, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void moveRightDoublePointTest() {
        TrackElement[][] actualTrack = new TrackElement[][]{
                new TrackElement[]{SQUARE_POINT, SQUARE_POINT, EMPTY}
        };
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT}
        };
        assertTwoTrack(expectedTrack, rightMover.moveRight(actualTrack));
    }
}