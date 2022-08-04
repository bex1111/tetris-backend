package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.TetrisStepFactory;
import org.bexterlab.tetrisbackend.core.exception.CannotRotateException;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.TrackTestUtil.assertTwoTrack;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class RightRotatorUnitTest {

    private TetrisStepFactory tetrisStepFactory;

    @BeforeEach
    void setUp() {
        tetrisStepFactory = new TetrisStepFactory();
    }

    @Test
    void rotateThreeLongElement1Test() {
        TrackElement[][] actualTrack = tetrisStepFactory.rotateRight(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_DOWN_RIGHT}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void rotateThreeLongElement2Test() {
        TrackElement[][] actualTrack = tetrisStepFactory.rotateRight(new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, THREE_LONG_ELEMENT_DOWN_RIGHT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_DOWN_LEFT, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void rotateThreeLongElement3Test() {
        TrackElement[][] actualTrack = tetrisStepFactory.rotateRight(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_DOWN_LEFT, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_LEFT, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void rotateThreeLongElement4Test() {
        TrackElement[][] actualTrack = tetrisStepFactory.rotateRight(new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_LEFT, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void rotateSquareTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.rotateRight(new TrackElement[][]{
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
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void canNotRotateThreeLongElementNoSpaceTest() {
        Assertions.assertThrows(CannotRotateException.class, () -> tetrisStepFactory.rotateRight(new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        }));
    }

    @Test
    void canNotRotateThreeLongElementCollideTest() {
        Assertions.assertThrows(CannotRotateException.class, () -> tetrisStepFactory.rotateRight(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{POINT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        }));
    }

    @Test
    void rotateThreeLongLElementWithPointInTheTrackTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.rotateRight(new TrackElement[][]{
                new TrackElement[]{THREE_LONG_ELEMENT_UP_LEFT, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, EMPTY},
                new TrackElement[]{POINT, EMPTY, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, THREE_LONG_ELEMENT_UP_RIGHT},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY},
                new TrackElement[]{POINT, EMPTY, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void rotatePyramidElementTest() {
        TrackElement[][] actualTrack = tetrisStepFactory.rotateRight(new TrackElement[][]{
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_UP_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, EMPTY, EMPTY},
                new TrackElement[]{THREE_LONG_ELEMENT_MIDDLE_LEFT, THREE_LONG_ELEMENT_MIDDLE_MIDDLE, THREE_LONG_ELEMENT_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, THREE_LONG_ELEMENT_DOWN_MIDDLE, EMPTY}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }
}