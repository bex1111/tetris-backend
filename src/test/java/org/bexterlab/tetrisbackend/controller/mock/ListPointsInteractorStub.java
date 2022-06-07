package org.bexterlab.tetrisbackend.controller.mock;

import org.bexterlab.tetrisbackend.controller.ListPointsInteractor;
import org.bexterlab.tetrisbackend.entity.User;

import java.util.List;

public class ListPointsInteractorStub implements ListPointsInteractor {

    public static final List<User> USERS = List.of(new User().setUsername("testU1").setPoints(1L), new User().setUsername("testU2").setPoints(2L));

    @Override
    public List<User> list() {
        return USERS;
    }
}
