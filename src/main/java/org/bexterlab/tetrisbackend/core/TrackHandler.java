package org.bexterlab.tetrisbackend.core;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.core.TrackElement.EMPTY;

public class TrackHandler {

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

    public TrackElement[][] moveRight(TrackElement[][] track) {
        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length - 1; j++) {
                if (track[i][j].canMoveToTheSide && track[i][j + 1] == EMPTY) {
                    track[i][j + 1] = track[i][j];
                    track[i][j] = EMPTY;
                }
            }
        }
        return track;
    }

    public TrackElement[][] moveLeft(TrackElement[][] track) {
        for (int i = 0; i < track.length; i++) {
            for (int j = 1; j < track[i].length; j++) {
                if (track[i][j].canMoveToTheSide && track[i][j - 1] == EMPTY) {
                    track[i][j - 1] = track[i][j];
                    track[i][j] = EMPTY;
                }
            }
        }
        return track;
    }

    public TrackElement[][] rotateLeft(TrackElement[][] track) {
        TrackElement[][] leftRotatedTrack = deepCopy(track);
        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length; j++) {
                leftRotatedTrack
                        [i + track[i][j].rotateLeftRowIndex]
                        [j + track[i][j].rotateLeftColumnIndex] = track[i][j].getLeftNewType();
                leftRotatedTrack[i][j] = track
                        [i + track[i][j].rotateLeftRowIndex]
                        [j + track[i][j].rotateLeftColumnIndex];
            }
        }
        return leftRotatedTrack;
    }

    private TrackElement[][] deepCopy(TrackElement[][] track) {
        return Arrays.stream(track).map(TrackElement[]::clone).toArray(x -> track.clone());
    }
}
