package org.bexterlab.tetrisbackend.gateway.store;

import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.bexterlab.tetrisbackend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

class StoreImplTest {

    private static final String TEST_USERNAME = "test";
    private StoreImpl store;
    private CopyOnWriteArrayList<Game> gameList;
    private CopyOnWriteArrayList<User> scoreBoard;

    @BeforeEach
    void setUp() {
        gameList = new CopyOnWriteArrayList<>();
        scoreBoard = new CopyOnWriteArrayList<>();
        store = new StoreImpl(gameList, scoreBoard);
    }

    @Test
    void hasGameWithUserTest() {
        Assertions.assertFalse(store.hasGameWithUser(TEST_USERNAME));
        gameList.add(new Game().setUser(new User().setUsername(TEST_USERNAME)));
        Assertions.assertTrue(store.hasGameWithUser(TEST_USERNAME));
        Assertions.assertFalse(store.hasGameWithUser("asd"));
    }

    @Test
    void createNewGameTest() {
        Assertions.assertTrue(gameList.isEmpty());
        store.createNewGame(new Game());
        Assertions.assertFalse(gameList.isEmpty());
        Assertions.assertEquals(1L, gameList.size());
    }

    @Test
    void findGamesTest() {
        gameList.add(generateBaseGameWithUser());
        Assertions.assertEquals(gameList, store.findGames());
    }

    @Test
    void storeNewTetrisElementTest() {
        gameList.add(generateBaseGameWithUser()
                .setTetrisElements(new TetrisElements()
                        .setCurrent(TetrisElement.LEFT_L)
                        .setNext(TetrisElement.STRAIGHT)));
        store.storeNewTetrisElement(TEST_USERNAME, TetrisElement.SQUARE);
        TetrisElements actualTetrisElements = gameList.get(0).getTetrisElements();
        Assertions.assertEquals(TetrisElement.STRAIGHT, actualTetrisElements.getCurrent());
        Assertions.assertEquals(TetrisElement.SQUARE, actualTetrisElements.getNext());
    }

    @Test
    void storeNewTrackTest() {
        TrackElement[][] trackElements = new TrackElement[][]{};
        gameList.add(generateBaseGameWithUser().setTrack(new TrackElement[][]{}));
        store.storeNewTrack(TEST_USERNAME, trackElements);
        Assertions.assertEquals(trackElements, gameList.get(0).getTrack());
    }

    @Test
    void hasGameTest() {
        Assertions.assertFalse(store.hasGame());
        gameList.add(new Game());
        Assertions.assertTrue(store.hasGame());
    }

    @Test
    void removeGameTest() {
        gameList.add(generateBaseGameWithUser());
        Assertions.assertFalse(gameList.isEmpty());
        store.removeGame(TEST_USERNAME);
        Assertions.assertTrue(gameList.isEmpty());
    }

    @Test
    void findNextMovementTest() {
        ConcurrentLinkedQueue<Movement> movementQueue = new ConcurrentLinkedQueue<>();
        movementQueue.add(Movement.MOVE_LEFT);
        gameList.add(generateBaseGameWithUser().setMovementQueue(movementQueue));
        Assertions.assertEquals(Movement.MOVE_LEFT, store.findNextMovement(TEST_USERNAME));
    }

    @Test
    void findTrackByUserTest() {
        TrackElement[][] trackElements = new TrackElement[][]{};
        gameList.add(generateBaseGameWithUser().setTrack(trackElements));
        Assertions.assertEquals(trackElements, store.findTrackByUser(TEST_USERNAME));
    }

    @Test
    void findNextTetrisElementTest() {
        gameList.add(generateBaseGameWithUser()
                .setTetrisElements(new TetrisElements()
                        .setNext(TetrisElement.STRAIGHT)));
        Assertions.assertEquals(TetrisElement.STRAIGHT, store.findNextTetrisElement(TEST_USERNAME));
    }

    @Test
    void hasGameWithUserAndTokenTest() {
        String testToken = "testToken";
        Assertions.assertFalse(store.hasGameWithUserAndToken(TEST_USERNAME, testToken));
        gameList.add(new Game().setUser(new User().setUsername(TEST_USERNAME).setToken(testToken)));
        Assertions.assertTrue(store.hasGameWithUserAndToken(TEST_USERNAME, testToken));
        Assertions.assertFalse(store.hasGameWithUserAndToken(TEST_USERNAME, "asd"));
        Assertions.assertFalse(store.hasGameWithUserAndToken("asd", testToken));
    }

    @Test
    void findUsernamesTest() {
        Assertions.assertTrue(store.findUsernames().isEmpty());
        gameList.add(new Game().setUser(new User().setUsername(TEST_USERNAME)));
        gameList.add(new Game().setUser(new User().setUsername(TEST_USERNAME + 1)));
        Assertions.assertEquals(List.of(TEST_USERNAME, TEST_USERNAME + 1), store.findUsernames());
    }

    @Test
    void storePointTest() {
        gameList.add(new Game().setUser(new User().setUsername(TEST_USERNAME).setPoints(0L)));
        store.storePoint(TEST_USERNAME, 10L);
        Assertions.assertEquals(10L, gameList.get(0).getUser().getPoints());
        store.storePoint(TEST_USERNAME, 10L);
        Assertions.assertEquals(20L, gameList.get(0).getUser().getPoints());
    }

    @Test
    void addPlayerIntoScoreBoardTest() {
        Game game = generateBaseGameWithUser();
        gameList.add(game);
        store.addPlayerIntoScoreBoard(TEST_USERNAME);
        Assertions.assertEquals(1L, scoreBoard.size());
        Assertions.assertEquals(game.getUser(), scoreBoard.get(0));
    }

    @Test
    void addNewTest() {
        ConcurrentLinkedQueue<Movement> movementQueue = new ConcurrentLinkedQueue<>();
        gameList.add(generateBaseGameWithUser().setMovementQueue(movementQueue));
        store.addNew(TEST_USERNAME, Movement.MOVE_LEFT);
        Assertions.assertEquals(Movement.MOVE_LEFT, movementQueue.poll());
    }

    @Test
    void countTest() {
        ConcurrentLinkedQueue<Movement> movementQueue = new ConcurrentLinkedQueue<>();
        movementQueue.add(Movement.MOVE_LEFT);
        gameList.add(generateBaseGameWithUser().setMovementQueue(movementQueue));
        Assertions.assertEquals(1L, store.countMovement(TEST_USERNAME));
        movementQueue.add(Movement.MOVE_LEFT);
        Assertions.assertEquals(2L, store.countMovement(TEST_USERNAME));
    }

    @Test
    void countUserTest() {
        gameList.add(generateBaseGameWithUser());
        Assertions.assertEquals(1L, store.countUser());
        gameList.add(generateBaseGameWithUser());
        Assertions.assertEquals(2L, store.countUser());
    }

    @Test
    void findUsersTest() {
        scoreBoard.add(generateBaseGameWithUser().getUser());
        Assertions.assertEquals(1, store.findUsers().size());
        Assertions.assertEquals(scoreBoard.get(0), store.findUsers().get(0));
    }

    private Game generateBaseGameWithUser() {
        return new Game().setUser(new User().setUsername(TEST_USERNAME));
    }
}