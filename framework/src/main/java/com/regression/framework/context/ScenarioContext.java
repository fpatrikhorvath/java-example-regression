package com.regression.framework.context;

import com.regression.framework.rest.response.GenericErrorResponse;
import com.regression.framework.rest.response.ResponseErrorEnum;
import com.regression.framework.service.util.MapperService;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ScenarioScope
public class ScenarioContext {
    private final MapperService mapperService;
    private final HashMap<String, Object> contextObjectMap = new HashMap<>();
    private ResponseErrorEnum response = null;

    public ScenarioContext(final MapperService mapperService) {
        this.mapperService = mapperService;
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

    public void storeErrorResponse(final Response response) {
        GenericErrorResponse genericErrorResponse = mapperService.mutateObject(response, GenericErrorResponse.class);
        this.response = ResponseErrorEnum.getByMessage(genericErrorResponse.getError());
    }
}
