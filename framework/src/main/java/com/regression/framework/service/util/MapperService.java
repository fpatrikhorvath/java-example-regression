package com.regression.framework.service.util;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@ScenarioScope
public class MapperService {
    private static final ObjectMapper mapper = new ObjectMapper();

    public <T> T mutateObject(final Response response, final Class<T> clazz) {
        try {
            return mapper.readValue(response.getBody().asString(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> mutateObjectList(final Response response,
                                        final Class<T> clazz) {
        try {
            String responseBody = response.getBody().asString();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
