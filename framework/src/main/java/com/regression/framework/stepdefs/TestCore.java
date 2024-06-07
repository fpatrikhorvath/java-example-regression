package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.service.BookService;
import com.regression.framework.service.UserService;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(loader = SpringBootContextLoader.class, value = {"classpath:spring.xml"})
@SpringBootTest(classes = TestCore.class)
public class TestCore {

    protected static final String RESPONSE_CODE_CHECK_MESSAGE = "Expected response code does not match with actual.";
    protected final ScenarioContext scenarioContext;
    private final UserLayerContextStore userLayerContextStore;

    public TestCore(final UserLayerContextStore userLayerContextStore, final ScenarioContext scenarioContext) {
        this.userLayerContextStore = userLayerContextStore;
        this.scenarioContext = scenarioContext;
    }

    protected UserService getUserService() {
        return userLayerContextStore.getUserService();
    }

    protected BookService getBookService() {
        return userLayerContextStore.getBookService();
    }
}