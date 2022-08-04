package org.bexterlab.tetrisbackend.gateway.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

class ScoreBoardStoreImplUnitTest {

    private static final String TEST_USERNAME = "test";
    private static final Long TEST_POINTS = 1L;
    private ScoreBoardStoreImpl store;
    private ConcurrentHashMap<String, Long> scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ConcurrentHashMap<>();
        store = new ScoreBoardStoreImpl(scoreBoard);
    }

    @Test
    void addPlayerIntoScoreBoardTest() {

        store.addPlayerIntoScoreBoard(TEST_USERNAME, TEST_POINTS);
        Assertions.assertEquals(TEST_POINTS, scoreBoard.size());
        Assertions.assertEquals(1, scoreBoard.get(TEST_USERNAME));
    }

    @Test
    void addMultiplyGameIntoScoreBoardTest() {
        store.addPlayerIntoScoreBoard(TEST_USERNAME, TEST_POINTS);
        store.addPlayerIntoScoreBoard(TEST_USERNAME, TEST_POINTS);
        store.addPlayerIntoScoreBoard(TEST_USERNAME, TEST_POINTS);
        Assertions.assertEquals(1L, scoreBoard.size());
        Assertions.assertEquals(TEST_POINTS * 3L, scoreBoard.get(TEST_USERNAME));
    }

    @Test
    void findScoreBoardTest() {
        final long point = 20L;
        scoreBoard.put(TEST_USERNAME, point);
        Assertions.assertEquals(1, store.finScoreBoard().size());
        Assertions.assertEquals(point, store.finScoreBoard().get(TEST_USERNAME));
    }
}