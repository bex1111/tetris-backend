package org.bexterlab.tetrisbackend.gateway.store;

import org.bexterlab.tetrisbackend.core.GameStore;
import org.bexterlab.tetrisbackend.core.MovementStore;
import org.bexterlab.tetrisbackend.core.UserStore;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.bexterlab.tetrisbackend.entity.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class StoreImpl implements GameStore, MovementStore, UserStore {

    private final CopyOnWriteArrayList<Game> gameList;
    private final CopyOnWriteArrayList<User> scoreBoard;

    public StoreImpl(CopyOnWriteArrayList<Game> gameList, CopyOnWriteArrayList<User> scoreBoard) {
        this.gameList = gameList;
        this.scoreBoard = scoreBoard;
    }

    @Override
    public boolean hasGameWithUser(String username) {
        return gameList.stream().anyMatch(x -> x.user().username().equals(username));
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
    public void storeNewTetrisElement(String username, TetrisElement nextTetrisElement) {
        Game game = findGameByUsername(username);
        gameList.add(new Game(game.user(),
                game.track(),
                game.movementQueue(),
                new TetrisElements(
                        game.tetrisElements().next(),
                        nextTetrisElement)));
        gameList.remove(game);
    }

    @Override
    public void storeNewTrack(String username, TrackElement[][] track) {
        Game game = findGameByUsername(username);
        gameList.add(new Game(game.user(),
                track,
                game.movementQueue(),
                game.tetrisElements()));
        gameList.remove(game);
    }

    @Override
    public boolean hasGame() {
        return !gameList.isEmpty();
    }
    
    @Override
    public void removeGame(String username) {
        gameList.remove(findGameByUsername(username));
    }

    @Override
    public Movement findNextMovement(String username) {
        return findGameByUsername(username).movementQueue().poll();
    }

    @Override
    public TrackElement[][] findTrackByUser(String username) {
        return findGameByUsername(username).track();
    }

    @Override
    public TetrisElement findNextTetrisElement(String username) {
        return findGameByUsername(username).tetrisElements().next();
    }

    @Override
    public boolean hasGameWithUserAndToken(String username, String token) {
        return gameList.stream()
                .anyMatch(x -> x.user().username().equals(username) &&
                        x.user().token().equals(token));
    }

    @Override
    public List<String> findUsernames() {
        return gameList.stream().map(x -> x.user().username()).collect(Collectors.toList());
    }


    @Override
    public void storePoint(String username, Long point) {
        Game game = findGameByUsername(username);
        gameList.add(new Game(
                new User(
                        game.user().username(),
                        game.user().token(),
                        game.user().points() + point),
                game.track(),
                game.movementQueue(),
                game.tetrisElements()));
        gameList.remove(game);
    }

    @Override
    public void addPlayerIntoScoreBoard(String username) {
        scoreBoard.add(findGameByUsername(username).user());
    }

    @Override
    public void addNew(String username, Movement movement) {
        findGameByUsername(username).movementQueue().add(movement);
    }

    @Override
    public int count(String username) {
        return findGameByUsername(username).movementQueue().size();
    }

    private Game findGameByUsername(String username) {
        return gameList.stream().filter(x -> x.user().username().equals(username))
                .findFirst().orElseThrow(() -> new AssertionError("Not find user"));
    }
}
