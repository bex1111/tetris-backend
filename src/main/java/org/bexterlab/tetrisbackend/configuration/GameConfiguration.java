package org.bexterlab.tetrisbackend.configuration;

import java.util.Objects;

import static java.util.Objects.isNull;

public class GameConfiguration {

    private static final int DEAD_ROW_INDEX = 3;
    private long maxUserCount;
    private long gameTickTime;
    private int trackWidth;
    private int trackHeight;
    private int round;
    private int gameTimeInMinutes;

    public int getGameTimeInMinutes() {
        return gameTimeInMinutes;
    }

    public GameConfiguration setGameTimeInMinutes(int gameTimeInMinutes) {
        if (gameTimeInMinutes < 1) {
            throw new AssertionError(String.format("Invalid gameTimeInMinutes %s", gameTimeInMinutes));
        }
        this.gameTimeInMinutes = gameTimeInMinutes;
        return this;
    }

    public Long getMaxUserCount() {
        return maxUserCount;
    }

    public GameConfiguration setMaxUserCount(Long maxUserCount) {
        this.maxUserCount = Objects.requireNonNull(maxUserCount);
        return this;
    }

    public int getTrackWidth() {
        return trackWidth;
    }

    public GameConfiguration setTrackWidth(Integer trackWidth) {
        if (isNull(trackWidth) || trackWidth < 6) {
            throw new AssertionError(String.format("Invalid track width %s", trackWidth));
        }
        this.trackWidth = trackWidth;
        return this;
    }

    public int getTrackHeight() {
        return trackHeight;
    }

    public GameConfiguration setTrackHeight(Integer trackHeight) {
        if (isNull(trackHeight) || trackHeight < 10) {
            throw new AssertionError(String.format("Invalid track height %s", trackHeight));
        }
        this.trackHeight = trackHeight;
        return this;
    }

    public long getGameTickTime() {
        return gameTickTime;
    }

    public GameConfiguration setGameTickTime(Long gameTickTime) {
        this.gameTickTime = Objects.requireNonNull(gameTickTime);
        return this;
    }

    public int getDeadRowIndex() {
        return DEAD_ROW_INDEX;
    }

    public int getRound() {
        return round;
    }

    public GameConfiguration setRound(int round) {
        if (round < 1) {
            throw new AssertionError("Round must be higher then zero!");
        }
        this.round = round;
        return this;
    }
}
