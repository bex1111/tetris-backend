package org.bexterlab.tetrisbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class TetrisAppRunner {

    private final ConfigurableApplicationContext tetrisApp;

    public TetrisAppRunner() {
        this.tetrisApp = SpringApplication.run(TetrisApplication.class,
                "--spring.config.location=classpath:/tetris-application.yml");
    }
}
