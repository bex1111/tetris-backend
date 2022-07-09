package org.bexterlab.tetrisbackend.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

class UserInformationStoreImplTest {

    public static final String USERNAME = "username";
    public static final String NOT_EXIST = "notExist" + USERNAME;
    private UserInformationStoreImpl userInformationStore;
    private ConcurrentHashMap<String, Integer> userRound;

    @BeforeEach
    void setUp() {
        userRound = new ConcurrentHashMap<>();
        userInformationStore = new UserInformationStoreImpl(userRound);
        userRound.put(USERNAME, 1);
    }

    @Test
    void countExistUserRoundTest() {
        Assertions.assertEquals(1, userInformationStore.countUserRound(USERNAME));
    }

    @Test
    void countNotExistUserRoundTest() {
        Assertions.assertEquals(0, userInformationStore.countUserRound(NOT_EXIST));
    }

    @Test
    void saveRoundExistingUserTest() {
        userInformationStore.saveRound(USERNAME);
        Assertions.assertEquals(2, userRound.get(USERNAME));
    }

    @Test
    void saveRoundNotExistingUserTest() {
        userInformationStore.saveRound(NOT_EXIST);
        Assertions.assertEquals(1, userRound.get(NOT_EXIST));
    }
}