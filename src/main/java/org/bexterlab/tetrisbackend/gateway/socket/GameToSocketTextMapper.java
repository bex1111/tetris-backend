package org.bexterlab.tetrisbackend.gateway.socket;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;

public class GameToSocketTextMapper {

    private final ObjectMapper objectMapper;

    public GameToSocketTextMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String map(Game game) {

        try {
            return objectMapper.writeValueAsString(
                    new TrackDto(mapTrack(game.track()),
                            game.tetrisElements().current(),
                            game.tetrisElements().next()));
        } catch (JsonProcessingException e) {
            //FIXME handle
            throw new RuntimeException(e);
        }
    }

    private TrackElementDto[][] mapTrack(TrackElement[][] track) {
        TrackElementDto[][] mappedTrack = new TrackElementDto[track.length][];
        for (int i = 0; i < track.length; i++) {
            mappedTrack[i] = new TrackElementDto[track[i].length];
            for (int j = 0; j < track[i].length; j++) {
                switch (track[i][j]) {
                    case POINT -> {
                        mappedTrack[i][j] = TrackElementDto.POINT;
                    }
                    case EMPTY -> {
                        mappedTrack[i][j] = TrackElementDto.EMPTY;
                    }
                    default -> {
                        mappedTrack[i][j] = TrackElementDto.ELEMENT;
                    }
                }
            }
        }
        return mappedTrack;
    }

    private enum TrackElementDto {
        EMPTY,
        POINT,
        ELEMENT;
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    private record TrackDto(TrackElementDto[][] track, TetrisElement current,
                            TetrisElement next) {
    }
}
