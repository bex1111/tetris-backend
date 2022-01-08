package org.bexterlab.tetrisbackend.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HealthSystemTest extends BaseControllerTest {

    @Test
    public void successTest() {
        Assertions.assertDoesNotThrow(restHelper::callHeath);
    }
}
