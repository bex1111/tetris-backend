package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.TrackSender;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrackSenderSpy implements TrackSender {

    public final List<Game> gameList;

    public TrackSenderSpy() {
        gameList = new CopyOnWriteArrayList<>();
    }

    @Override
    public void sendTrackForUser(Game game) {
        gameList.add(game);
    }
}
