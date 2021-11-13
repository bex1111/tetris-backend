package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.TrackElement;

import static org.bexterlab.tetrisbackend.core.TrackElement.EMPTY;

public class BaseSideMover {


    public TrackElement[][] moveSide(TrackElement[][] track, int) {
        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length; j++) {
                if (track[i][j].canMoveToTheSide && track[i][j + 1] == EMPTY) {
                    track[i][j + 1] = track[i][j];
                    track[i][j] = EMPTY;
                }
            }
        }
        return track;
    }
}
