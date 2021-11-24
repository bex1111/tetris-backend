package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.entity.TrackElement.*;

class ElementColliderTest {

    @Test
    void collideTest() {
        ElementCollider elementCollider = new ElementCollider();
        TrackElement[][] actualTrack = elementCollider.collide(new TrackElement[][]{
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
        ElementCollider elementCollider = new ElementCollider();
        TrackElement[][] actualTrack = elementCollider.collide(new TrackElement[][]{
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
        ElementCollider elementCollider = new ElementCollider();
        TrackElement[][] actualTrack = elementCollider.collide(new TrackElement[][]{
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