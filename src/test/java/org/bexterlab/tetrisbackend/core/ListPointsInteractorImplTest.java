package org.bexterlab.tetrisbackend.core;

import org.bexterlab.tetrisbackend.core.mock.UserStoreFake;
import org.bexterlab.tetrisbackend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ListPointsInteractorImplTest {

    @Test
    void listTest() {
        List<String> calledMethodNames = new ArrayList<>();
        UserStoreFake userStore = new UserStoreFake(calledMethodNames);
        List<User> users = new ListPointsInteractorImpl(userStore).list();
        Assertions.assertEquals(Collections.emptyList(), users);
        Assertions.assertEquals(1, calledMethodNames.size());
        Assertions.assertEquals("findUsers", calledMethodNames.get(0));
    }
}