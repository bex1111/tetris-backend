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
        for (int i = 0; i < 29; i++) {
            user = new User("valid_user_" + i, "token", 0L);
            gameStore.createNewGame(new Game(user, null, null, null));
        }
        long actualUserCount = gameStore.getUserCount();

        Assertions.assertEquals(expectedUserCount, actualUserCount);
    }

}