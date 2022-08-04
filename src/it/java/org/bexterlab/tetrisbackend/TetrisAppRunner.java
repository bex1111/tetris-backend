package org.bexterlab.tetrisbackend;

import org.bexterlab.tetrisbackend.configuration.GameConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class TetrisAppRunner {

    private final ConfigurableApplicationContext tetrisApp;

    public TetrisAppRunner() {
        this.tetrisApp = SpringApplication.run(new Class[]{TetrisApplication.class},
                new String[]{"--spring.config.location=src/it/resources/tetris-application.yml"});
    }

    public void stop() {
        tetrisApp.stop();
    }

    public long getDelayTime() {
        return tetrisApp.getBean(GameConfiguration.class).getGameTickTime();
    }
}
