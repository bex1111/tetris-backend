package org.bexterlab.tetrisbackend.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.bexterlab.tetrisbackend.core.TrackElement.*;


class TrackTest {


    private TrackHandler trackHandler;

    @BeforeEach
    void setUp() {
        trackHandler = new TrackHandler();
    }

    @Test
    void moveDown() {
        TrackElement[][] actualTrack = trackHandler.moveDown(new TrackElement[][]{
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
    void moveDown2() {
        TrackElement[][] actualTrack = trackHandler.moveDown(new TrackElement[][]{
                new TrackElement[]{POINT, POINT},
                new TrackElement[]{EMPTY, POINT}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{EMPTY, POINT},
                new TrackElement[]{POINT, POINT}
        };
        assertTwoTrack(expectedTrack, actualTrack);
    }

    @Test
    void moveRight() {
        TrackElement[][] actualTrack = trackHandler.moveRight(new TrackElement[][]{
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
    void moveLeft() {
        TrackElement[][] actualTrack = trackHandler.moveLeft(new TrackElement[][]{
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
    void rotateSquare() {
        TrackElement[][] actualTrack = trackHandler.rotateLeft(new TrackElement[][]{
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
    void rotateL1() {
        TrackElement[][] actualTrack = trackHandler.rotateLeft(new TrackElement[][]{
                new TrackElement[]{EMPTY, L_UP_MIDDLE, L_UP_RIGHT},
                new TrackElement[]{EMPTY, L_MIDDLE_MIDDLE, EMPTY},
                new TrackElement[]{EMPTY, L_DOWN_MIDDLE, EMPTY}
        });
        TrackElement[][] expectedTrack = new TrackElement[][]{
                new TrackElement[]{L_UP_LEFT, EMPTY, EMPTY},
                new TrackElement[]{L_MIDDLE_LEFT, L_MIDDLE_MIDDLE, L_MIDDLE_RIGHT},
                new TrackElement[]{EMPTY, EMPTY, EMPTY}
        };
        assertTwoTrack(actualTrack, expectedTrack);
    }


    private void assertTwoTrack(TrackElement[][] actualTrack, TrackElement[][] expectedTrack) {
        Assertions.assertEquals(expectedTrack.length, actualTrack.length);
        for (int i = 0; i < expectedTrack.length; i++) {
            Assertions.assertEquals(expectedTrack[i].length, actualTrack[i].length);
            for (int j = 0; j < expectedTrack[i].length; j++) {
                Assertions.assertEquals(expectedTrack[i][j], actualTrack[i][j],
                        generateReadableStringFromTrack(expectedTrack) + "\n-------\n" +
                                generateReadableStringFromTrack(actualTrack));
            }
        }
    }

    private String generateReadableStringFromTrack(TrackElement[][] track) {
        return Arrays.stream(track).map(Arrays::deepToString).collect(Collectors.joining("\n"));
    }
}