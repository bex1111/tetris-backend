package org.bexterlab.tetrisbackend.gateway;

import org.bexterlab.tetrisbackend.configuration.MainConfiguration;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.bexterlab.tetrisbackend.gateway.socket.GameToSocketTextMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameToSocketTextMapperTest {

    @Test
    void mapperTest() {
        Assertions.assertEquals("{\"track\":[[\"EMPTY\"],[\"ELEMENT\"],[\"POINT\"]]," +
                        "\"current\":\"SQUARE\",\"next\":\"LEFT_PYRAMID\"}",
                new GameToSocketTextMapper(new MainConfiguration().objectMapper())
                        .map(new Game(null,
                                new TrackElement[][]{new TrackElement[]{TrackElement.EMPTY},
                                        new TrackElement[]{TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE},
                                        new TrackElement[]{TrackElement.POINT}},
                                null,
                                new TetrisElements(TetrisElement.SQUARE,
                                        TetrisElement.LEFT_PYRAMID))));
    }
}