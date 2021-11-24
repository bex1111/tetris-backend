package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.maintenance.NewElementSpawner;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TetrisElements;

import java.util.Collections;
import java.util.List;

public class GameStoreImpl implements GameStore {

    private final List<Game> gameList;

    public GameStoreImpl(List<Game> gameList) {
        this.gameList = Collections.synchronizedList(gameList);
    }

    @Override
    public boolean hasGameWithUser(String username) {
        return gameList.stream().anyMatch(x -> x.player().username().equals(username));
    }

    @Override
    public void createNewGame(Game game) {
        gameList.add(game);
    }

    @Override
    public List<Game> getGames() {
        return gameList;
    }

    @Override
    public void storeNewTetrisElement(Game game, NewElementSpawner.TetrisElement nextTetrisElement) {
        game = new Game(game.player(),
                game.track(),
                game.movementQueue(),
                new TetrisElements(game.tetrisElements().next(), nextTetrisElement));
    }

    @Override
    public boolean hasGame() {
        return !gameList.isEmpty();
    }


}
