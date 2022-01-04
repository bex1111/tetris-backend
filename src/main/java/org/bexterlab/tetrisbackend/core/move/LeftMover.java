package org.bexterlab.tetrisbackend.core.move;

public class LeftMover extends BaseSideMover {

    public TrackElement[][] moveLeft(TrackElement[][] track) {
        return move(track);
    }

    protected TrackElement[][] moveToSide(TrackElement[][] track) {
        final int direction = -1;
        TrackElement[][] movedTrack = deepCopy(track);
        for (int i = 0; i < movedTrack.length; i++) {
            for (int j = 0; j < movedTrack[i].length; j++) {
                if (movedTrack[i][j].isNotFix) {
                    checkIsElementCollideWithPoint(movedTrack, direction, i, j);
                    TrackElement temp = movedTrack[i][j + direction];
                    movedTrack[i][j + direction] = movedTrack[i][j];
                    movedTrack[i][j] = temp;
                }
            }
        }
        return movedTrack;
    }
}
