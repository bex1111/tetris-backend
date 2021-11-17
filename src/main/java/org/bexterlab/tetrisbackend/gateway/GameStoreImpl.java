package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.entity.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void createNewGame(Game game) {
        gameList.add(game);
    }

    @Override
    public List<Game> getGames() {
        return gameList;
    }


}
