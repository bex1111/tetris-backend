package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class FullRowCleanerTest {

    @Test
    void clearRowTest() {
        TetrisStepFactory tetrisStepFactory = new TetrisStepFactory();
        TrackElement[][] actualTrack = tetrisStepFactory.clearFullRow(new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, POINT},
                new TrackElement[]{POINT, EMPTY, POINT, EMPTY},
                new TrackElement[]{POINT, POINT, POINT, POINT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{POINT, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{POINT, EMPTY, POINT, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }
}