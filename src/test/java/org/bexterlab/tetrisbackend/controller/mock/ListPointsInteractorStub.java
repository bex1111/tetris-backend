package org.bexterlab.tetrisbackend.controller.mock;

import org.bexterlab.tetrisbackend.controller.ListPointsInteractor;

import java.util.Map;

public class ListPointsInteractorStub implements ListPointsInteractor {

    public static final Map<String, Long> USERS =
            Map.of("testU1", 1L,
                    "testU2", 5L,
                    "testU3", 2L);

    @Override
    public Map<String, Long> list() {
        return USERS;
    }
}
