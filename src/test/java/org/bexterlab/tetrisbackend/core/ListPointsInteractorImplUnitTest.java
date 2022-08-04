package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.mock.ScoreBoardStoreFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class ListPointsInteractorImplUnitTest {

    @Test
    void listTest() {
        List<String> calledMethodNames = new ArrayList<>();
        ScoreBoardStoreFake userStore = new ScoreBoardStoreFake(calledMethodNames);
        Map<String, Long> users = new ListPointsInteractorImpl(userStore).list();
        Assertions.assertEquals(Collections.emptyMap(), users);
        Assertions.assertEquals(1, calledMethodNames.size());
        Assertions.assertEquals("finScoreBoard", calledMethodNames.get(0));
    }
}