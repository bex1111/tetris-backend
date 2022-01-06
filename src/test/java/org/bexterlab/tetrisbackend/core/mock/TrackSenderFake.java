package org.bexterlab.tetrisbackend.core.mock;

import org.bexterlab.tetrisbackend.core.TrackSender;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.nonNull;

public class TrackSenderFake implements TrackSender {

    public final List<Game> gameList;
    public volatile RuntimeException exception;

    public TrackSenderFake() {
        gameList = new CopyOnWriteArrayList<>();
    }

    @Override
    public void sendTrackForUser(List<Game> game) {
        if (nonNull(exception)) {
            throw exception;
        }
        gameList.addAll(game);
    }
}
