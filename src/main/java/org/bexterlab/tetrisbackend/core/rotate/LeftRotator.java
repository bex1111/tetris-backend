package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.TrackElement;

public class LeftRotator extends BaseRotator {
    @Override
    protected TrackElement[][] replaceElement(TrackElement[][] rotatedTrack, TrackElement[][] track, int i, int j) {
        rotatedTrack
                [i + track[i][j].rotateLeftRowIndex]
                [j + track[i][j].rotateLeftColumnIndex] = track[i][j].getLeftNewType();
        rotatedTrack[i][j] = track
                [i + track[i][j].rotateLeftRowIndex]
                [j + track[i][j].rotateLeftColumnIndex];
        return rotatedTrack;
    }
}
