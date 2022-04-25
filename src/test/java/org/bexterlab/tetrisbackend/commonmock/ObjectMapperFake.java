package org.bexterlab.tetrisbackend.commonmock;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class ObjectMapperFake extends ObjectMapper {

    public JsonParseException jsonParseException;

    @Override
    public String writeValueAsString(Object value) throws JsonProcessingException {
        if (Objects.nonNull(jsonParseException)) {
            throw jsonParseException;
        }
        return super.writeValueAsString(value);
    }
}
