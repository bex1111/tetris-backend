package org.bexterlab.tetrisbackend.gamer;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class GamerAppRunner {

    private final ConfigurableApplicationContext gamerApp;

    public GamerAppRunner() {
        this.gamerApp = SpringApplication.run(new Class[]{GamerApplication.class},
                new String[]{"--spring.config.location=src/it/resources/gamer-application.yml"});
    }
}
