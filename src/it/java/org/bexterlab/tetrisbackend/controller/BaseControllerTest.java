package org.bexterlab.tetrisbackend.controller;

import org.bexterlab.tetrisbackend.TetrisApplication;
import org.bexterlab.tetrisbackend.helper.RestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class BaseControllerTest {


    protected static final String TEST_USER = "test_user";
    protected final RestHelper restHelper;
    private ConfigurableApplicationContext configurableApplicationContext;

    public BaseControllerTest() {
        this.restHelper = new RestHelper();
    }

    @BeforeEach
    void setUp() {
        configurableApplicationContext = SpringApplication.run(TetrisApplication.class);
        localSetUp();
    }

    protected void localSetUp() {

    }

    @AfterEach
    void tearDown() {
        configurableApplicationContext.close();
    }

}
