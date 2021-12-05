package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TetrisElements;

import java.util.List;

public class GameStoreImpl implements GameStore {

    private final List<Game> gameList;

    public GameStoreImpl(List<Game> gameList) {
        this.gameList = gameList;
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
    public Game storeNewTetrisElement(Game game, TetrisElement nextTetrisElement) {
        gameList.remove(game);
        game = new Game(game.player(),
                game.track(),
                game.movementQueue(),
                new TetrisElements(game.tetrisElements().next(), nextTetrisElement));
        gameList.add(game);
        return game;
    }

    @Override
    public void storeNewTrack(Game game, TrackElement[][] track) {
        gameList.remove(game);
        game = new Game(game.player(),
                track,
                game.movementQueue(),
                game.tetrisElements());
        gameList.add(game);
    }

    @Override
    public boolean hasGame() {
        return !gameList.isEmpty();
    }


}
