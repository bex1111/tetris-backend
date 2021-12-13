package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.gateway.store.GameStoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameStoreImplTest {


    private GameStore gameStore;

    @BeforeEach
    void setUp() {
        gameStore = new GameStoreImpl(new ArrayList<>());
    }

    @Test
    public void hasGameWithUserTest() {
        Game game = new Game(null, null, null, null);
        gameStore.createNewGame(game);
        Assertions.assertEquals(game, gameStore.getGames().get(0));
    }

    @Test
    public void hasNotGameWithUserTest() {
        Assertions.assertFalse(gameStore.hasGameWithUser("test"));
    }
}