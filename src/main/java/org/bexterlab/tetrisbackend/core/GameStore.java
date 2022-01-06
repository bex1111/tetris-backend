package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.Movement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;

public interface GameStore {

    void createNewGame(Game game);

    List<Game> getGames();

    void storeNewTetrisElement(String username, TetrisElement tetrisElement);

    void storeNewTrack(String username, TrackElement[][] track);

    boolean hasGame();

    void removeGame(String username);

    Movement findNextMovement(String username);

    TrackElement[][] findTrackByUser(String username);

    TetrisElement findNextTetrisElement(String username);
}
