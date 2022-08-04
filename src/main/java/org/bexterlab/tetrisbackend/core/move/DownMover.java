package org.bexterlab.tetrisbackend.core.move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

public class DownMover {

    private final List<Integer[]> elementPosition;

    public DownMover() {
        this.elementPosition = new ArrayList<>();
    }

    public TrackElement[][] moveDown(TrackElement[][] track) {
        for (int i = 0; i < track.length - 1; i++) {
            collectElementFromTheRow(track, i);
            if (isNextRowClear(track[i + 1])) {
                moveDownFixElement(track, i);
                i++;
            }
        }
        moveDownElements(track);
        return track;
    }

    private void moveDownFixElement(TrackElement[][] track, int i) {
        for (int j = 0; j < track[i].length; j++) {
            if (!track[i][j].isNotFix) {
                moveDownFixElement(track, i, j);
            }
        }
    }

    private void moveDownFixElement(TrackElement[][] track, int i, int j) {
        if (track[i][j] == POINT) {
            replaceElementInTrack(track, i, j);
            for (int k = i + 1; k < track.length - 1; k++) {
                if (isBelowElementEmpty(track, k, j) && !isNextRowClear(track[k + 1])) {
                    replaceElementInTrack(track, k, j);
                }
            }
        } else {
            replaceElementInTrack(track, i, j);
        }
    }

    private boolean isBelowElementEmpty(TrackElement[][] track, int k, int j) {
        return track.length - 1 > k && track[k + 1][j] == EMPTY;
    }

    private void moveDownElements(TrackElement[][] track) {
        elementPosition.stream()
                .sorted((x, y) -> Integer.compare(y[0], x[0]))
                .forEach(x -> replaceElementInTrack(track, x[0], x[1]));
    }

    private boolean isNextRowClear(TrackElement[] nextRow) {
        return Arrays.stream(nextRow).allMatch(x -> x == EMPTY);
    }

    private void replaceElementInTrack(TrackElement[][] track, int i, int j) {
        TrackElement temporaryTrackElement = track[i + 1][j];
        track[i + 1][j] = track[i][j];
        track[i][j] = temporaryTrackElement;
    }

    private void collectElementFromTheRow(TrackElement[][] track, int i) {
        for (int j = 0; j < track[i].length; j++) {
            if (track[i][j].isNotFix) {
                elementPosition.add(new Integer[]{i, j});
            }
        }
    }

}
