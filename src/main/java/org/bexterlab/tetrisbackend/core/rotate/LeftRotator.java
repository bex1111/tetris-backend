package org.bexterlab.tetrisbackend.core.rotate;

import org.bexterlab.tetrisbackend.core.move.TrackElement;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

public class LeftRotator extends BaseRotator {

    @Override
    protected boolean isRotateColliedWithPoint(TrackElement[][] rotatedTrack, int i, int j) {
        return rotatedTrack
                [i + rotatedTrack[i][j].rotateLeftRowIndex]
                [j + rotatedTrack[i][j].rotateLeftColumnIndex] == POINT;
    }

    @Override
    protected TrackElement[][] replaceElement(TrackElement[][] rotatedTrack, int i, int j) {
        final int newRowPosition = i + rotatedTrack[i][j].rotateLeftRowIndex;
        final int newColumnPosition = j + rotatedTrack[i][j].rotateLeftColumnIndex;
        TrackElement tempTrackElement = rotatedTrack[newRowPosition][newColumnPosition];
        rotatedTrack[newRowPosition][newColumnPosition] = rotatedTrack[i][j].getLeftNewType();
        rotatedTrack[i][j] = tempTrackElement;
        return rotatedTrack;
    }

}
