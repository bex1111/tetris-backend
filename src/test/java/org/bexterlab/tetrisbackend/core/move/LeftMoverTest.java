package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.TrackElement;
import org.bexterlab.tetrisbackend.core.exception.CanNotMoveException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackElement.*;
import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;

class LeftMoverTest {

    LeftMover leftMover;

    @BeforeEach
    void setUp() {
        leftMover = new LeftMover();
    }

    @Test
    void moveLeft() {
        TrackElement[][] actualTrack = leftMover.moveLeft(new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT},
                new TrackElement[]{POINT, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void notMoveLeft() {
        TrackElement[][] actualTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT},
                new TrackElement[]{SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, EMPTY}};

        Assertions.assertThrows(CanNotMoveException.class, () -> leftMover.moveLeft(actualTrack));
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT},
                new TrackElement[]{SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }
}