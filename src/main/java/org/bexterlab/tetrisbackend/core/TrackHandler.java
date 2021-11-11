package org.bexterlab.tetrisbackend.core;

public class TrackHandler {

    static final String EMPTY = "EMPTY";
//    private final String[][] track;
//
//
//    public Track(int rowNumber, int columnNumber) {
//        this.track = initTrack(rowNumber, columnNumber);
//    }
//
//    private String[][] initTrack(int rowNumber, int columnNumber) {
//        String[][] emptyTrack = new String[rowNumber][];
//        for (String[] trackRow : emptyTrack) {
//            trackRow = new String[columnNumber];
//            for (String trackColumn : trackRow) {
//                trackColumn = EMPTY_CELL;
//            }
//        }
//        return emptyTrack;
//    }

    public String[][] moveDown(String[][] track) {
        for (int i = 0; i < track.length - 1; i++) {
            for (int j = 0; j < track[i].length; j++) {
                if (!track[i][j].equals("EMPTY") && track[i + 1][j].equals("EMPTY")) {
                    track[i + 1][j] = track[i][j];
                    track[i][j] = "EMPTY";
                }
            }
        }
        return track;
    }

    public String[][] moveRight(String[][] track) {
        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length - 1; j++) {
                if (track[i][j].equals("ELEMENT") && track[i][j + 1].equals("EMPTY")) {
                    track[i][j + 1] = track[i][j];
                    track[i][j] = "EMPTY";
                }
            }
        }
        return track;
    }

    public String[][] moveLeft(String[][] track) {
        for (int i = 0; i < track.length; i++) {
            for (int j = 1; j < track[i].length; j++) {
                if (track[i][j].equals("ELEMENT") && track[i][j - 1].equals("EMPTY")) {
                    track[i][j - 1] = track[i][j];
                    track[i][j] = "EMPTY";
                }
            }
        }
        return track;
    }
}
