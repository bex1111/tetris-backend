package org.bexterlab.tetrisbackend.gateway.socket;

import com.fasterxml.jackson.core.JsonParseException;
import org.bexterlab.tetrisbackend.commonmock.ObjectMapperFake;
import org.bexterlab.tetrisbackend.configuration.MainConfiguration;
import org.bexterlab.tetrisbackend.core.maintenance.TetrisElement;
import org.bexterlab.tetrisbackend.core.move.TrackElement;
import org.bexterlab.tetrisbackend.entity.Game;
import org.bexterlab.tetrisbackend.entity.TetrisElements;
import org.bexterlab.tetrisbackend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class GameToSocketTextMapperUnitTest {

    @Test
    void mapperTest() {
        Assertions.assertEquals("[{\"track\":[[\"EMPTY\"],[\"ELEMENT\"],[\"POINT\"]]," +
                        "\"current\":\"SQUARE\",\"next\":\"LEFT_PYRAMID\",\"username\":\"test\",\"point\":10}]",
                new GameToSocketTextMapper(new MainConfiguration().objectMapper(), 1)
                        .map(List.of(new Game()
                                .setTrack(new TrackElement[][]{
                                        new TrackElement[]{TrackElement.EMPTY},
                                        new TrackElement[]{TrackElement.EMPTY},
                                        new TrackElement[]{TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE},
                                        new TrackElement[]{TrackElement.POINT}})
                                .setTetrisElements(new TetrisElements()
                                        .setCurrent(TetrisElement.SQUARE)
                                        .setNext(TetrisElement.LEFT_PYRAMID))
                                .setUser(new User()
                                        .setPoints(10L)
                                        .setUsername("test")))));
    }

    @Test
    void mapperExceptionTest() {
        ObjectMapperFake objectMapperFake = new ObjectMapperFake();
        objectMapperFake.jsonParseException = new JsonParseException(null, "hupsz");
        Assertions.assertThrows(CannotGenerateSocketMessageException.class, () ->
                new GameToSocketTextMapper(objectMapperFake, 1)
                        .map(List.of(new Game()
                                .setTrack(new TrackElement[][]{new TrackElement[]{TrackElement.EMPTY},
                                        new TrackElement[]{TrackElement.THREE_LONG_ELEMENT_MIDDLE_MIDDLE},
                                        new TrackElement[]{TrackElement.POINT}})
                                .setTetrisElements(new TetrisElements()
                                        .setCurrent(TetrisElement.SQUARE)
                                        .setNext(TetrisElement.LEFT_PYRAMID))
                                .setUser(new User()
                                        .setPoints(10L)
                                        .setUsername("test")))));
    }
}