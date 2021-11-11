package org.bexterlab.tetrisbackend.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TrackTest {

    public static final String ELEMENT = "ELEMENT";
    public static final String EMPTY = "EMPTY";
    public static final String POINT = "POINT";

    @Test
    void moveDown() {
        TrackHandler trackHandler = new TrackHandler();
        String[][] track = trackHandler.moveDown(new String[][]{
                new String[]{POINT},
                new String[]{EMPTY}
        });
        Assertions.assertEquals(track[0][0], EMPTY);
        Assertions.assertEquals(track[1][0], POINT);
    }

    @Test
    void moveDown2() {
        TrackHandler trackHandler = new TrackHandler();
        String[][] track = trackHandler.moveDown(new String[][]{
                new String[]{POINT, POINT},
                new String[]{EMPTY, POINT}
        });
        Assertions.assertEquals(track[0][0], EMPTY);
        Assertions.assertEquals(track[0][1], POINT);
        Assertions.assertEquals(track[1][0], POINT);
        Assertions.assertEquals(track[1][1], POINT);
    }

    @Test
    void moveRight() {
        TrackHandler trackHandler = new TrackHandler();
        String[][] track = trackHandler.moveRight(new String[][]{
                new String[]{ELEMENT, EMPTY},
                new String[]{POINT, EMPTY}
        });
        Assertions.assertEquals(track[0][0], EMPTY);
        Assertions.assertEquals(track[0][1], ELEMENT);
        Assertions.assertEquals(track[1][0], POINT);
        Assertions.assertEquals(track[1][1], EMPTY);
    }

    @Test
    void moveLeft() {
        TrackHandler trackHandler = new TrackHandler();
        String[][] track = trackHandler.moveLeft(new String[][]{
                new String[]{EMPTY, ELEMENT},
                new String[]{POINT, EMPTY}
        });
        Assertions.assertEquals(track[0][0], ELEMENT);
        Assertions.assertEquals(track[0][1], EMPTY);
        Assertions.assertEquals(track[1][0], POINT);
        Assertions.assertEquals(track[1][1], EMPTY);
    }
}