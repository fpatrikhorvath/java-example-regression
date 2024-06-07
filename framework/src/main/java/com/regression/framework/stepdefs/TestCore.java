package com.regression.framework.stepdefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.regression.framework.context.ScenarioContext;
import com.regression.framework.service.BookService;
import com.regression.framework.service.UserService;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.List;

@CucumberContextConfiguration
@ContextConfiguration(loader = SpringBootContextLoader.class, value = {"classpath:spring.xml"})
@SpringBootTest(classes = TestCore.class)
public class TestCore {

    protected static final String RESPONSE_CODE_CHECK_MESSAGE = "Expected response code does not match with actual.";
    private static final ObjectMapper mapper = new ObjectMapper();
    protected final ScenarioContext scenarioContext;
    private final UserLayerContextStore userLayerContextStore;

    public TestCore(final UserLayerContextStore userLayerContextStore, final ScenarioContext scenarioContext) {
        this.userLayerContextStore = userLayerContextStore;
        this.scenarioContext = scenarioContext;
    }

    protected <T> T mutateObject(final Response response, final Class<T> clazz) {
        try {
            return mapper.readValue(response.getBody().asString(), clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <T> List<T> mutateObjectList(final Response response, final Class<T> clazz) {
        try {
            String responseBody = response.getBody().asString();
            return mapper.readValue(responseBody, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected UserService getUserService() {
        return userLayerContextStore.getUserService();
    }

    protected BookService getBookService() {
        return userLayerContextStore.getBookService();
    }
}