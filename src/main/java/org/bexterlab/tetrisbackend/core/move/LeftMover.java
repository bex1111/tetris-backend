package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.entity.TrackElement;

public class LeftMover extends BaseSideMover {

    public TrackElement[][] moveLeft(TrackElement[][] track) {
        return move(track, -1);
    }
}
