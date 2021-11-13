package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.TrackElement;

import static org.bexterlab.tetrisbackend.core.TrackElement.EMPTY;

public class DownMover {


    public TrackElement[][] moveDown(TrackElement[][] track) {
        for (int i = 0; i < track.length - 1; i++) {
            for (int j = 0; j < track[i].length; j++) {
                if (track[i][j] != EMPTY && track[i + 1][j] == EMPTY) {
                    track[i + 1][j] = track[i][j];
                    track[i][j] = EMPTY;
                }
            }
        }
        return track;
    }

}
