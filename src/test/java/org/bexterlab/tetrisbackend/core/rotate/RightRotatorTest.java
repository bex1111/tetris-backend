package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.exception.CanNotRotateException;
import org.bexterlab.tetrisbackend.entity.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.entity.TrackElement.*;

class RightRotatorTest {

    private RightRotator rightRotator;

    @BeforeEach
    void setUp() {
        rightRotator = new RightRotator();

    }

    @Test
    void rotateRightThreeLongElement1Test() {
        TrackElement[][] actualTrack = rightRotator.rotate(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_DOWN_RIGHT}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }


    @Test
    void rotateRightThreeLongElement2Test() {
        TrackElement[][] actualTrack = rightRotator.rotate(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_DOWN_RIGHT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_DOWN_LEFT, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void rotateRightThreeLongElement3Test() {
        TrackElement[][] actualTrack = rightRotator.rotate(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_DOWN_LEFT, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_LEFT, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void rotateRightThreeLongElement4Test() {
        TrackElement[][] actualTrack = rightRotator.rotate(new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_LEFT, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void rotateSquareTest() {
        TrackElement[][] actualTrack = rightRotator.rotate(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, SQUARE_POINT, SQUARE_POINT, EMPTY},
                new TrackElement[]{EMPTY, EMPTY, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }

    @Test
    void canNotRotateRightThreeLongElementNoSpaceTest() {
        Assertions.assertThrows(CanNotRotateException.class, () -> rightRotator.rotate(new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        }));
    }


    @Test
    void canNotRotateRightThreeLongElementCollideTest() {
        Assertions.assertThrows(CanNotRotateException.class, () -> rightRotator.rotate(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{POINT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        }));
    }
}