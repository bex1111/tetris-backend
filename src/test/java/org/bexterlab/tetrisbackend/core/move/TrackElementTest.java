package org.bexterlab.tetrisbackend.core.move;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.*;

class TrackElementTest {

    @Test
    void point() {
        Assertions.assertEquals(0, POINT.rotateRightRowIndex);
        Assertions.assertEquals(0, POINT.rotateRightColumnIndex);
        Assertions.assertEquals(0, POINT.rotateLeftRowIndex);
        Assertions.assertEquals(0, POINT.rotateLeftColumnIndex);
        Assertions.assertFalse(POINT.isNotFix);
        Assertions.assertEquals(POINT.getLeftNewType(), POINT);
        Assertions.assertEquals(POINT.getRightNewType(), POINT);
    }

    @Test
    void emptyTest() {
        Assertions.assertEquals(0, EMPTY.rotateRightRowIndex);
        Assertions.assertEquals(0, EMPTY.rotateRightColumnIndex);
        Assertions.assertEquals(0, EMPTY.rotateLeftRowIndex);
        Assertions.assertEquals(0, EMPTY.rotateLeftColumnIndex);
        Assertions.assertFalse(EMPTY.isNotFix);
        Assertions.assertEquals(EMPTY.getLeftNewType(), EMPTY);
        Assertions.assertEquals(EMPTY.getRightNewType(), EMPTY);
    }

    @Test
    void squarePointTest() {
        Assertions.assertEquals(0, SQUARE_POINT.rotateRightRowIndex);
        Assertions.assertEquals(0, SQUARE_POINT.rotateRightColumnIndex);
        Assertions.assertEquals(0, SQUARE_POINT.rotateLeftRowIndex);
        Assertions.assertEquals(0, SQUARE_POINT.rotateLeftColumnIndex);
        Assertions.assertTrue(SQUARE_POINT.isNotFix);
        Assertions.assertEquals(SQUARE_POINT.getLeftNewType(), SQUARE_POINT);
        Assertions.assertEquals(SQUARE_POINT.getRightNewType(), SQUARE_POINT);
    }

    @Test
    void threeLongElementMiddleMiddleTest() {
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_MIDDLE_MIDDLE.rotateRightRowIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_MIDDLE_MIDDLE.rotateRightColumnIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_MIDDLE_MIDDLE.rotateLeftRowIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_MIDDLE_MIDDLE.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_MIDDLE_MIDDLE.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_MIDDLE_MIDDLE.getLeftNewType(), THREE_LONG_ELEMENT_MIDDLE_MIDDLE);
        Assertions.assertEquals(THREE_LONG_ELEMENT_MIDDLE_MIDDLE.getRightNewType(), THREE_LONG_ELEMENT_MIDDLE_MIDDLE);
    }

    @Test
    void threeLongElementUpLeftTest() {
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_UP_LEFT.rotateRightRowIndex);
        Assertions.assertEquals(2, THREE_LONG_ELEMENT_UP_LEFT.rotateRightColumnIndex);
        Assertions.assertEquals(2, THREE_LONG_ELEMENT_UP_LEFT.rotateLeftRowIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_UP_LEFT.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_UP_LEFT.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_UP_LEFT.getLeftNewType(), THREE_LONG_ELEMENT_DOWN_LEFT);
        Assertions.assertEquals(THREE_LONG_ELEMENT_UP_LEFT.getRightNewType(), THREE_LONG_ELEMENT_UP_RIGHT);
    }

    @Test
    void threeLongElementMiddleLeftTest() {
        Assertions.assertEquals(-1, THREE_LONG_ELEMENT_MIDDLE_LEFT.rotateRightRowIndex);
        Assertions.assertEquals(1, THREE_LONG_ELEMENT_MIDDLE_LEFT.rotateRightColumnIndex);
        Assertions.assertEquals(1, THREE_LONG_ELEMENT_MIDDLE_LEFT.rotateLeftRowIndex);
        Assertions.assertEquals(1, THREE_LONG_ELEMENT_MIDDLE_LEFT.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_MIDDLE_LEFT.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_MIDDLE_LEFT.getLeftNewType(), THREE_LONG_ELEMENT_DOWN_MIDDLE);
        Assertions.assertEquals(THREE_LONG_ELEMENT_MIDDLE_LEFT.getRightNewType(), THREE_LONG_ELEMENT_UP_MIDDLE);
    }

    @Test
    void threeLongElementDownLeftTest() {
        Assertions.assertEquals(-2, THREE_LONG_ELEMENT_DOWN_LEFT.rotateRightRowIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_DOWN_LEFT.rotateRightColumnIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_DOWN_LEFT.rotateLeftRowIndex);
        Assertions.assertEquals(2, THREE_LONG_ELEMENT_DOWN_LEFT.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_DOWN_LEFT.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_DOWN_LEFT.getLeftNewType(), THREE_LONG_ELEMENT_DOWN_RIGHT);
        Assertions.assertEquals(THREE_LONG_ELEMENT_DOWN_LEFT.getRightNewType(), THREE_LONG_ELEMENT_UP_LEFT);
    }

    @Test
    void threeLongElementUpMiddleTest() {
        Assertions.assertEquals(1, THREE_LONG_ELEMENT_UP_MIDDLE.rotateRightRowIndex);
        Assertions.assertEquals(1, THREE_LONG_ELEMENT_UP_MIDDLE.rotateRightColumnIndex);
        Assertions.assertEquals(1, THREE_LONG_ELEMENT_UP_MIDDLE.rotateLeftRowIndex);
        Assertions.assertEquals(-1, THREE_LONG_ELEMENT_UP_MIDDLE.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_UP_MIDDLE.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_UP_MIDDLE.getLeftNewType(), THREE_LONG_ELEMENT_MIDDLE_LEFT);
        Assertions.assertEquals(THREE_LONG_ELEMENT_UP_MIDDLE.getRightNewType(), THREE_LONG_ELEMENT_MIDDLE_RIGHT);
    }

    @Test
    void threeLongElementDownMiddleTest() {
        Assertions.assertEquals(-1, THREE_LONG_ELEMENT_DOWN_MIDDLE.rotateRightRowIndex);
        Assertions.assertEquals(-1, THREE_LONG_ELEMENT_DOWN_MIDDLE.rotateRightColumnIndex);
        Assertions.assertEquals(-1, THREE_LONG_ELEMENT_DOWN_MIDDLE.rotateLeftRowIndex);
        Assertions.assertEquals(1, THREE_LONG_ELEMENT_DOWN_MIDDLE.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_DOWN_MIDDLE.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_DOWN_MIDDLE.getLeftNewType(), THREE_LONG_ELEMENT_MIDDLE_RIGHT);
        Assertions.assertEquals(THREE_LONG_ELEMENT_DOWN_MIDDLE.getRightNewType(), THREE_LONG_ELEMENT_MIDDLE_LEFT);
    }

    @Test
    void threeLongElementUpRightTest() {
        Assertions.assertEquals(2, THREE_LONG_ELEMENT_UP_RIGHT.rotateRightRowIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_UP_RIGHT.rotateRightColumnIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_UP_RIGHT.rotateLeftRowIndex);
        Assertions.assertEquals(-2, THREE_LONG_ELEMENT_UP_RIGHT.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_UP_RIGHT.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_UP_RIGHT.getLeftNewType(), THREE_LONG_ELEMENT_UP_LEFT);
        Assertions.assertEquals(THREE_LONG_ELEMENT_UP_RIGHT.getRightNewType(), THREE_LONG_ELEMENT_DOWN_RIGHT);
    }

    @Test
    void threeLongElementMiddleRightTest() {
        Assertions.assertEquals(1, THREE_LONG_ELEMENT_MIDDLE_RIGHT.rotateRightRowIndex);
        Assertions.assertEquals(-1, THREE_LONG_ELEMENT_MIDDLE_RIGHT.rotateRightColumnIndex);
        Assertions.assertEquals(-1, THREE_LONG_ELEMENT_MIDDLE_RIGHT.rotateLeftRowIndex);
        Assertions.assertEquals(-1, THREE_LONG_ELEMENT_MIDDLE_RIGHT.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_MIDDLE_RIGHT.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_MIDDLE_RIGHT.getLeftNewType(), THREE_LONG_ELEMENT_UP_MIDDLE);
        Assertions.assertEquals(THREE_LONG_ELEMENT_MIDDLE_RIGHT.getRightNewType(), THREE_LONG_ELEMENT_DOWN_MIDDLE);
    }

    @Test
    void threeLongElementDownRightTest() {
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_DOWN_RIGHT.rotateRightRowIndex);
        Assertions.assertEquals(-2, THREE_LONG_ELEMENT_DOWN_RIGHT.rotateRightColumnIndex);
        Assertions.assertEquals(-2, THREE_LONG_ELEMENT_DOWN_RIGHT.rotateLeftRowIndex);
        Assertions.assertEquals(0, THREE_LONG_ELEMENT_DOWN_RIGHT.rotateLeftColumnIndex);
        Assertions.assertTrue(THREE_LONG_ELEMENT_DOWN_RIGHT.isNotFix);
        Assertions.assertEquals(THREE_LONG_ELEMENT_DOWN_RIGHT.getLeftNewType(), THREE_LONG_ELEMENT_UP_RIGHT);
        Assertions.assertEquals(THREE_LONG_ELEMENT_DOWN_RIGHT.getRightNewType(), THREE_LONG_ELEMENT_DOWN_LEFT);
    }

}