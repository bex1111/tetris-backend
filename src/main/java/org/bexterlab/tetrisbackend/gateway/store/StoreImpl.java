package org.bexterlab.tetrisbackend.gateway.store;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.MovementStore;
import org.bexterlab.tetrisbackend.core.UserStore;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.Movement;
import org.bexterlab.tetrisbackend.entity.TetrisElements;

import java.util.List;

public class StoreImpl implements GameStore, MovementStore, UserStore {

    private final List<Game> gameList;

    public StoreImpl(List<Game> gameList) {
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

    @Override
    public boolean hasGameWithUserAndToken(String username, String token) {
        return gameList.stream()
                .anyMatch(x -> x.player().username().equals(username) &&
                        x.player().token().equals(token));
    }

    @Override
    public void addNew(String username, Movement movement) {
        findUser(username).movementQueue().add(movement);
    }

    @Override
    public int count(String username) {
        return findUser(username).movementQueue().size();
    }

    private Game findUser(String username) {
        return gameList.stream().filter(x -> x.player().username().equals(username))
                .findFirst().orElseThrow(() -> new AssertionError("Not find user"));
    }


}
