package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.entity.TrackElement.*;

class FullRowCleanerTest {

    @Test
    void clearRowTest() {
        FullRowCleaner fullRowCleaner = new FullRowCleaner();
        TrackElement[][] actualTrack = fullRowCleaner.clean(new TrackElement[][]{
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