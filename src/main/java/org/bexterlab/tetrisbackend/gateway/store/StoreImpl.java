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

    public StoreImpl(CopyOnWriteArrayList<Game> gameList,
                     CopyOnWriteArrayList<User> scoreBoard) {
        this.gameList = gameList;
        this.scoreBoard = scoreBoard;
    }

    @Override
    public boolean hasGameWithUser(String username) {
        return gameList.stream().anyMatch(x -> x.getUser().getUsername().equals(username));
    }

    @Override
    public void createNewGame(Game game) {
        gameList.add(game);
    }

    @Override
    public List<Game> findGames() {
        return gameList;
    }

    @Override
    public void storeNewTetrisElement(String username, TetrisElement nextTetrisElement) {
        Game game = findGameByUsername(username);
        gameList.add(new Game()
                .setUser(game.getUser())
                .setTrack(game.getTrack())
                .setMovementQueue(game.getMovementQueue())
                .setTetrisElements(new TetrisElements()
                        .setCurrent(game.getTetrisElements().getNext())
                        .setNext(nextTetrisElement)));
        gameList.remove(game);
    }

    @Override
    public void storeNewTrack(String username, TrackElement[][] track) {
        Game game = findGameByUsername(username);
        gameList.add(new Game()
                .setUser(game.getUser())
                .setTrack(track)
                .setMovementQueue(game.getMovementQueue())
                .setTetrisElements(game.getTetrisElements()));
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
        return findGameByUsername(username).getMovementQueue().poll();
    }

    @Override
    public TrackElement[][] findTrackByUser(String username) {
        return findGameByUsername(username).getTrack();
    }

    @Override
    public TetrisElement findNextTetrisElement(String username) {
        return findGameByUsername(username).getTetrisElements().getNext();
    }

    @Override
    public boolean hasGameWithUserAndToken(String username, String token) {
        return gameList.stream()
                .anyMatch(x -> x.getUser().getUsername().equals(username) &&
                        x.getUser().getToken().equals(token));
    }

    @Override
    public List<String> findUsernames() {
        return gameList.stream().map(x -> x.getUser().getUsername()).collect(Collectors.toList());
    }

    @Override
    public void storePoint(String username, Long point) {
        Game game = findGameByUsername(username);
        gameList.add(new Game()
                .setUser(new User()
                        .setUsername(game.getUser().getUsername())
                        .setToken(game.getUser().getToken())
                        .setPoints(game.getUser().getPoints() + point))
                .setTrack(game.getTrack())
                .setMovementQueue(game.getMovementQueue())
                .setTetrisElements(game.getTetrisElements()));
        gameList.remove(game);
    }

    @Override
    public void addPlayerIntoScoreBoard(String username) {
        scoreBoard.add(findGameByUsername(username).getUser());
    }

    @Override
    public void addNew(String username, Movement movement) {
        findGameByUsername(username).getMovementQueue().add(movement);
    }

    @Override
    public int countMovement(String username) {
        return findGameByUsername(username).getMovementQueue().size();
    }

    @Override
    public long countUser() {
        return gameList.size();
    }

    private Game findGameByUsername(String username) {
        return gameList.stream().filter(x -> x.getUser().getUsername().equals(username))
                .findFirst().orElseThrow(() -> new AssertionError("Not find user"));
    }
}
