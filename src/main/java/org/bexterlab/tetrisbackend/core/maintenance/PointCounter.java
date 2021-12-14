package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.core.move.TrackElement;

import java.util.Arrays;

public class PointCounter {
    public Long count(TrackElement[][] track) {
        return Arrays
                .stream(track)
                .map(row -> Arrays
                        .stream(row)
                        .filter(column -> column == TrackElement.EMPTY)
                        .count()).reduce(0L, Long::sum);
    }
}
