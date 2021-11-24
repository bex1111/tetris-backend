package org.bexterlab.tetrisbackend.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StartGameDto(@JsonProperty("username") String username) {
}
