package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;

public interface GameStore {
    void createNewGame(Game game);

    List<Game> getGames();

    Game storeNewTetrisElement(Game game, TetrisElement tetrisElement);

    void storeNewTrack(Game game, TrackElement[][] track);

    boolean hasGame();

    void removeGame(Game game);
}
