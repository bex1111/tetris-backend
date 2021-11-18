package org.bexterlab.tetrisbackend.core.element;

import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.entity.TrackElement.*;

class ElementColliderTest {

    @Test
    void clearCollideTest() {
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
    void clearNotCollideTest() {
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
}