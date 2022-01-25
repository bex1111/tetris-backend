package org.bexterlab.tetrisbackend.gateway.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                        new TrackDto()
                            .setTrack(mapTrack(game.getTrack()))
                            .setCurrent(game.getTetrisElements().getCurrent())
                            .setNext(game.getTetrisElements().getNext()))
                .collect(Collectors.toList());
    }

    private TrackElementDto[][] mapTrack(TrackElement[][] track) {
        TrackElementDto[][] mappedTrack = new TrackElementDto[track.length][];
        for (int i = 0; i < track.length; i++) {
            mappedTrack[i] = new TrackElementDto[track[i].length];
            for (int j = 0; j < track[i].length; j++) {
                switch (track[i][j]) {
                    case POINT:
                        mappedTrack[i][j] = TrackElementDto.POINT; break;
                    case EMPTY:
                        mappedTrack[i][j] = TrackElementDto.EMPTY; break;
                    default :
                        mappedTrack[i][j] = TrackElementDto.ELEMENT;
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

    public class TrackDto {
        private TrackElementDto[][] track;
        private TetrisElement current;
        private TetrisElement next;

        public TetrisElement getCurrent() {
            return current;
        }

        public TrackDto setCurrent(TetrisElement current) {
            this.current = current;
            return this;
        }

        public TetrisElement getNext() {
            return next;
        }

        public TrackDto setNext(TetrisElement next) {
            this.next = next;
            return this;
        }

        public TrackElementDto[][] getTrack() {
            return track;
        }

        public TrackDto setTrack(TrackElementDto[][] track) {
            this.track = track;
            return this;
        }
    }
}
