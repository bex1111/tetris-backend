package org.bexterlab.tetrisbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class TetrisAppRunner {

    private final ConfigurableApplicationContext tetrisApp;

    public TetrisAppRunner() {
        this.tetrisApp = SpringApplication.run(new Class[]{TetrisApplication.class},
                new String[]{"--spring.config.location=src/it/resources/tetris-application.yml"});
    }
}
