package org.bexterlab.tetrisbackend.core.element;

import org.bexterlab.tetrisbackend.core.TrackElement;

import java.util.Arrays;

import static org.bexterlab.tetrisbackend.core.TrackElement.EMPTY;
import static org.bexterlab.tetrisbackend.core.TrackElement.POINT;

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
