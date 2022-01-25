package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.gateway.store.StoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

class StoreImplTest {

    private StoreImpl gameStore;

    @BeforeEach
    void setUp() {
        gameStore = new StoreImpl(new CopyOnWriteArrayList<>(), new CopyOnWriteArrayList<>());
    }

    @Test
    public void hasGameWithUserTest() {
        Game game = new Game();
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
        for (int i = 0; i < 29; i++) {
            gameStore.createNewGame(new Game());
        }
        long actualUserCount = gameStore.getUserCount();

        Assertions.assertEquals(expectedUserCount, actualUserCount);
    }

}