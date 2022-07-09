package org.bexterlab.tetrisbackend.core.gateway;

import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;

public interface TrackSender {

    void sendTrackForUser(List<Game> games);

}
