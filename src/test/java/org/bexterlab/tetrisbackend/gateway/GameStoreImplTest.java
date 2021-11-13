package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameStoreImplTest {


    private GameStore gameStore;

    @BeforeEach
    void setUp() {
        gameStore = new GameStoreImpl();
    }

    @Test
    public void hasGameWithUserTest() {

        String username = "test";
        gameStore.createNewGame(username);
        Assertions.assertTrue(gameStore.hasGameWithUser(username));
    }

    @Test
    public void hasNotGameWithUserTest() {
        Assertions.assertFalse(gameStore.hasGameWithUser("test"));
    }
}