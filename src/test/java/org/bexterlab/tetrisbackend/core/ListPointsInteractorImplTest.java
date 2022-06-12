package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class ListPointsInteractorImplTest {

    @Test
    void listTest() {
        List<String> calledMethodNames = new ArrayList<>();
        UserStoreFake userStore = new UserStoreFake(calledMethodNames);
        Map<String, Long> users = new ListPointsInteractorImpl(userStore).list();
        Assertions.assertEquals(Collections.emptyMap(), users);
        Assertions.assertEquals(1, calledMethodNames.size());
        Assertions.assertEquals("finScoreBoard", calledMethodNames.get(0));
    }
}