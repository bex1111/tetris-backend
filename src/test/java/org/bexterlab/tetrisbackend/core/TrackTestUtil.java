package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TrackTestUtil {

    private TrackTestUtil() {
    }


    public static void assertTwoTrack(TrackElement[][] expectedTrack, TrackElement[][] actualTrack) {
        Assertions.assertEquals(expectedTrack.length, actualTrack.length);
        for (int i = 0; i < expectedTrack.length; i++) {
            Assertions.assertEquals(expectedTrack[i].length, actualTrack[i].length);
            for (int j = 0; j < expectedTrack[i].length; j++) {
                Assertions.assertEquals(expectedTrack[i][j], actualTrack[i][j],
                        generateReadableStringFromTrack(expectedTrack) + "\n-------\n" +
                                generateReadableStringFromTrack(actualTrack));
            }
        }
    }

    private static String generateReadableStringFromTrack(TrackElement[][] track) {
        return Arrays.stream(track).map(Arrays::deepToString).collect(Collectors.joining("\n"));
    }
}
