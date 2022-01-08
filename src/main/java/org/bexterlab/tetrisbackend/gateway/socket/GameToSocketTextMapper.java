package org.bexterlab.tetrisbackend.gateway.socket;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;

import java.util.List;
import java.util.stream.Collectors;

public class GameToSocketTextMapper {

    private final ObjectMapper objectMapper;

    public GameToSocketTextMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String map(List<Game> games) {
        try {
            return objectMapper.writeValueAsString(maopGameToTrackDto(games));
        } catch (JsonProcessingException e) {
            throw new CannotGenerateSocketMessageException(e);
        }
    }

    private List<TrackDto> maopGameToTrackDto(List<Game> games) {
        return games.stream().map(game ->
                        new TrackDto(mapTrack(game.track()),
                                game.tetrisElements().current(),
                                game.tetrisElements().next()))
                .collect(Collectors.toList());
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

    public enum TrackElementDto {
        EMPTY,
        POINT,
        ELEMENT;
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    public record TrackDto(@JsonProperty("track") TrackElementDto[][] track,
                           @JsonProperty("current") TetrisElement current,
                           @JsonProperty("next") TetrisElement next) {

    }
}
