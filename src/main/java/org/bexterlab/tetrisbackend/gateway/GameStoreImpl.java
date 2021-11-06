package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.entity.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class GameStoreImpl implements GameStore {

    private final List<Game> gameList;

    public GameStoreImpl() {
        this.gameList = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public boolean hasGameWithUser(String username) {
        return gameList.stream().anyMatch(x -> x.username().equals(username));
    }

    @Override
    public String createNewGame(String username) {
        Game game = new Game(username, UUID.randomUUID().toString());
        gameList.add(game);
        return game.token();
    }
}
