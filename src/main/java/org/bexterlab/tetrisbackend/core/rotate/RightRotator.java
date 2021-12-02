package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.move.TrackElement;

public class RightRotator extends BaseRotator {
    @Override
    protected TrackElement[][] replaceElement(TrackElement[][] rotatedTrack, TrackElement[][] track, int i, int j) {
        rotatedTrack
                [i + track[i][j].rotateRightRowIndex]
                [j + track[i][j].rotateRightColumnIndex] = track[i][j].getRightNewType();
        rotatedTrack[i][j] = track
                [i + track[i][j].rotateRightRowIndex]
                [j + track[i][j].rotateRightColumnIndex];
        return rotatedTrack;
    }
}
