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
        Assertions.assertEquals(USERS.get(0).getUsername(), scoreDtoList.get(0).getUsername());
        Assertions.assertEquals(USERS.get(0).getPoints(), scoreDtoList.get(0).getPoints());
        Assertions.assertEquals(USERS.get(1).getUsername(), scoreDtoList.get(1).getUsername());
        Assertions.assertEquals(USERS.get(1).getPoints(), scoreDtoList.get(1).getPoints());
    }
}