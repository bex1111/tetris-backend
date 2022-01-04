package org.bexterlab.tetrisbackend.core.move;

public class RightMover extends BaseSideMover {

    public TrackElement[][] moveRight(TrackElement[][] track) {
        return move(track);
    }

    protected TrackElement[][] moveToSide(TrackElement[][] track) {
        final int direction = 1;
        TrackElement[][] movedTrack = deepCopy(track);
        for (int i = 0; i < movedTrack.length; i++) {
            for (int j = movedTrack[i].length - 1; j >= 0; j--) {
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
