package org.bexterlab.tetrisbackend.core.maintenance;

import org.bexterlab.tetrisbackend.entity.TrackElement;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Optional;

public class TetrisElementLottery {
    private static final SecureRandom random = new SecureRandom();

    public Optional<NewElementSpawner.TetrisElement> drawIfNotElementInTheTrack(TrackElement[][] track) {
        if (Arrays.stream(track).allMatch(row -> Arrays.stream(row).noneMatch(column -> column.isNotFix))) {
            return Optional.of(NewElementSpawner.TetrisElement.values()[random.nextInt(NewElementSpawner.TetrisElement.values().length)]);
        }
        return Optional.empty();
    }

    public NewElementSpawner.TetrisElement draw() {
        return NewElementSpawner.TetrisElement.values()[random.nextInt(NewElementSpawner.TetrisElement.values().length)];
    }

}
