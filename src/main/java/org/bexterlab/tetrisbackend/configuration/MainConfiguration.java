package org.bexterlab.tetrisbackend.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {

    @Bean
    public Logger initLogger() {
        return LoggerFactory.getLogger("Tetris Logger");
    }

}
