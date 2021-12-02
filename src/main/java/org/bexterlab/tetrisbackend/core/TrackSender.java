package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.entity.Game;

public interface TrackSender {

    void sendTrackForUser(Game game);

}
