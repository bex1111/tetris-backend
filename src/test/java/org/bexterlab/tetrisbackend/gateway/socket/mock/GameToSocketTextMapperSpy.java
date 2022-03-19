package org.bexterlab.tetrisbackend.gateway.socket.mock;

import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.gateway.socket.GameToSocketTextMapper;

import java.util.List;

public class GameToSocketTextMapperSpy extends GameToSocketTextMapper {

    public List<Game> games;

    public GameToSocketTextMapperSpy() {
        super(null);
    }


    @Override
    public String map(List<Game> games) {
        this.games = games;
        return "testMappedMessage";
    }
}
