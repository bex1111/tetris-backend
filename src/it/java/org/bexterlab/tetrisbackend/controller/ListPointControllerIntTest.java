package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.controller.dto.PointsDto;
import org.bexterlab.tetrisbackend.gateway.store.ScoreBoardStoreImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ListPointControllerIntTest extends BaseControllerTest {


    @Test
    void emptyListTest() {
        Assertions.assertEquals(0, restHelper.callListPoints().size());
    }

    @Test
    void hasGameListTest() {
        configurableApplicationContext.getBean(ScoreBoardStoreImpl.class).addPlayerIntoScoreBoard("test", 5000L);
        configurableApplicationContext.getBean(ScoreBoardStoreImpl.class).addPlayerIntoScoreBoard("test2", 6000L);
        List<PointsDto> pointsDtos = restHelper.callListPoints();
        Assertions.assertEquals(5000, pointsDtos.get(0).points);
        Assertions.assertEquals("test", pointsDtos.get(0).username);
        Assertions.assertEquals(56000, pointsDtos.get(1).points);
        Assertions.assertEquals("test2", pointsDtos.get(0).username);

    }
}
