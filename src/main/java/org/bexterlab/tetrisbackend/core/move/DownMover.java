package org.bexterlab.tetrisbackend.core.move;

import org.bexterlab.tetrisbackend.core.TrackElement;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.core.TrackElement.EMPTY;

public class DownMover {


    public TrackElement[][] moveDown(TrackElement[][] track) {
        for (int i = 0; i < track.length - 1; i++) {
            if (Arrays.stream(track[i + 1]).allMatch(x -> x == EMPTY)) {
                for (int j = 0; j < track[i].length; j++) {
                    track[i + 1][j] = track[i][j];
                    track[i][j] = EMPTY;
                }
            }
        }
        return track;
    }

}
