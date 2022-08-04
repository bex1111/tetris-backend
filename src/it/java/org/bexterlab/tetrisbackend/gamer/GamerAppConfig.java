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

import static org.bexterlab.tetrisbackend.TestConstants.BASE_URL;

@Configuration
@Profile("Gamer")
public class GamerAppConfig {

    @Bean
    public ObjectMapper gamerAppObjectMapper() {
        return new Jackson2ObjectMapperBuilder().createXmlMapper(false).build();
    }

    @Bean
    public Logger gamerAppLogger() {
        return LoggerFactory.getLogger("Gamer App Logger");
    }

    @Bean
    public WebsocketClientEndpoint websocketClientEndpoint(ObjectMapper gamerAppObjectMapper,
                                                           Logger gamerAppLogger) throws URISyntaxException {
        return new WebsocketClientEndpoint(
                new URI("ws://" + BASE_URL + "/api/tetris"),
                gamerAppObjectMapper,
                gamerAppLogger);
    }
}
