package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.gateway.store.StoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import org.bexterlab.tetrisbackend.entity.User;

class StoreImplTest {

    private StoreImpl gameStore;

    @BeforeEach
    void setUp() {
        gameStore = new StoreImpl(new CopyOnWriteArrayList<>(), new CopyOnWriteArrayList<>());
    }

    @Test
    public void hasGameWithUserTest() {
        Game game = new Game(null, null, null, null);
        gameStore.createNewGame(game);
        Assertions.assertEquals(game, gameStore.getGames().get(0));
    }

    @Test
    @Disabled
    public void hasNotGameWithUserTest() {
        // Assertions.assertFalse(gameStore.hasGameWithUser("test"));
    }

    @Test
    public void testUserCountWhenPlayersHaveOneGames() {
        long expectedUserCount = 29;
        User user;
        for (int i = 0; i < 29; i++) { // berakunk 29 különböző nevű játékost 1-1 játékkal
            user = new User("valid_user_" + i, "token", 0L);
            gameStore.createNewGame(new Game(user, null, null, null));
        }
        long actualUserCount = gameStore.getUserCount();

        Assertions.assertEquals(expectedUserCount, actualUserCount);
    }

    @Test
    public void testUserCountWhenPlayersHaveMultipleGames() {
        long expectedUserCount = 3;
        User user1 = new User("valid_user_1", "token", 0L);
        User user2 = new User("valid_user_2", "token", 0L);
        User user3 = new User("valid_user_3", "token", 0L);
        for (int i = 0; i < 10; i++) { // berakunk 3 különböző nevű játékost 10-10 játékkal
            gameStore.createNewGame(new Game(user1, null, null, null));
            gameStore.createNewGame(new Game(user2, null, null, null));
            gameStore.createNewGame(new Game(user3, null, null, null));
        }

        long actualUserCount = gameStore.getUserCount();

        Assertions.assertEquals(expectedUserCount, actualUserCount);
    }

}