package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.controller.dto.PointsDto;
import org.bexterlab.tetrisbackend.controller.mock.ListPointsInteractorStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.bexterlab.tetrisbackend.controller.mock.ListPointsInteractorStub.USERS;

class ListPointsControllerTest {

    @Test
    void listpointsTest() {
        List<PointsDto> scoreDtoList = new ListPointsController(new ListPointsInteractorStub()).list();
        Assertions.assertEquals("testU2", scoreDtoList.get(0).getUsername());
        Assertions.assertEquals(USERS.get("testU2"), scoreDtoList.get(0).getPoints());
        Assertions.assertEquals("testU3", scoreDtoList.get(1).getUsername());
        Assertions.assertEquals(USERS.get("testU3"), scoreDtoList.get(1).getPoints());
        Assertions.assertEquals("testU1", scoreDtoList.get(2).getUsername());
        Assertions.assertEquals(USERS.get("testU1"), scoreDtoList.get(2).getPoints());
    }
}