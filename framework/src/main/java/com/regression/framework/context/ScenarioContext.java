package com.regression.framework.context;

import com.regression.framework.rest.response.ResponseErrorEnum;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ScenarioScope
public class ScenarioContext {
    private final HashMap<String, Object> contextObjectMap = new HashMap<>();
    private ResponseErrorEnum response = null;

    public ScenarioContext() {
    }

    public void storeContextObject(final String key, final Object object) {
        contextObjectMap.put(key, object);
    }

    public Object getContextObject(final String key) {
        return contextObjectMap.get(key);
    }

    public ResponseErrorEnum getResponse() {
        return response;
    }

    public void storeResponse(final Response response) {
        String responseBody = response.getBody().asString();
        this.response = ResponseErrorEnum.getByMessage(responseBody);
    }
}
