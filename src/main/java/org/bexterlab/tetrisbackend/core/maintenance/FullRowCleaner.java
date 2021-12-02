package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.core.move.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.move.TrackElement.POINT;

public class FullRowCleaner {

    public TrackElement[][] clean(TrackElement[][] track) {
        for (TrackElement[] row : track) {
            if (Arrays.stream(row).allMatch(x -> POINT == x)) {
                Arrays.fill(row, EMPTY);
            }
        }
        return track;
    }
}
