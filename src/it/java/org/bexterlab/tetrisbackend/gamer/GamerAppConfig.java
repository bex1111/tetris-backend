package org.bexterlab.tetrisbackend.gamer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("Gamer")
public class GamerAppConfig {

    @Bean
    public ObjectMapper gamerAppObjectMapper() {
        return new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
    }

    //TOD lehet érdemes majd vmi neved adni a logoknak
    @Bean
    public Logger gamerAppLogger() {
        return LoggerFactory.getLogger("Gamer App Logger");
    }

    //TODO event kezelés
    @Bean
    public WebsocketClientEndpoint websocketClientEndpoint(ObjectMapper gamerAppObjectMapper,
                                                           Logger gamerAppLogger) throws URISyntaxException {
        return new WebsocketClientEndpoint(
                new URI("ws://localhost:8080/tetris"),
                gamerAppObjectMapper,
                gamerAppLogger);
    }
}
