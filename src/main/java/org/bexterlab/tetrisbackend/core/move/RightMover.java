package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.entity.TrackElement;

public class RightMover extends BaseSideMover {

    public TrackElement[][] moveRight(TrackElement[][] track) {
        return move(track, 1);
    }
}
