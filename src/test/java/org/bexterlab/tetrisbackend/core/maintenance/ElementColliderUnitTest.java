package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class ElementColliderUnitTest {

    private TetrisStepFactory tetrisStepFactory;

    @BeforeEach
    void setUp() {
        tetrisStepFactory = new TetrisStepFactory();
    }

    @Test
    void collideTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.collideElement(new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, POINT},
                new TrackElement[]{POINT, EMPTY, POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, POINT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, POINT, POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, POINT},
                new TrackElement[]{POINT, EMPTY, POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, POINT}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void notCollideTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.collideElement(new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{POINT, EMPTY, POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, POINT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{POINT, EMPTY, POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, POINT}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void lastRowCollideTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.collideElement(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, POINT, POINT, EMPTY},
                new TrackElement[]{EMPTY, POINT, POINT, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }
}