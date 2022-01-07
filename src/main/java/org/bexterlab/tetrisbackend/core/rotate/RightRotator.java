package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.move.TrackElement;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

public class RightRotator extends BaseRotator {

    @Override
    protected TrackElement[][] replaceElement(TrackElement[][] rotatedTrack, int i, int j) {
        final int newRowPosition = i + rotatedTrack[i][j].rotateRightRowIndex;
        final int newColumnPosition = j + rotatedTrack[i][j].rotateRightColumnIndex;
        TrackElement tempTrackElement = rotatedTrack[newRowPosition][newColumnPosition];
        rotatedTrack[newRowPosition][newColumnPosition] = rotatedTrack[i][j].getRightNewType();
        rotatedTrack[i][j] = tempTrackElement;
        return rotatedTrack;
    }

    @Override
    protected boolean isRotateColliedWithPoint(TrackElement[][] rotatedTrack, int i, int j) {
        return rotatedTrack
                [i + rotatedTrack[i][j].rotateRightRowIndex]
                [j + rotatedTrack[i][j].rotateRightColumnIndex] == POINT;
    }

}
