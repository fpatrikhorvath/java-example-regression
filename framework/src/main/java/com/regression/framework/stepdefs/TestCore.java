package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.selenium.handler.*;
import com.regression.framework.service.BookService;
import com.regression.framework.service.UserService;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@CucumberContextConfiguration
@ContextConfiguration(loader = SpringBootContextLoader.class, value = {"classpath:spring.xml"})
@SpringBootTest(classes = TestCore.class)
public class TestCore {

    protected final ScenarioContext scenarioContext;
    private final UserLayerContextStore userLayerContextStore;
    private final ParabankPageStore parabankPageStore;

    public TestCore(final UserLayerContextStore userLayerContextStore,
                    final ScenarioContext scenarioContext,
                    final ParabankPageStore parabankPageStore) {
        this.userLayerContextStore = userLayerContextStore;
        this.scenarioContext = scenarioContext;
        this.parabankPageStore = parabankPageStore;
    }

    protected UserService getUserService() {
        return userLayerContextStore.getUserService();
    }

    protected BookService getBookService() {
        return userLayerContextStore.getBookService();
    }

    protected IndexPageHandler getIndexPageHandler() {
        return parabankPageStore.getIndexPageHandler();
    }

    protected RegisterPageHandler getRegisterPageHandler() {
        return parabankPageStore.getRegisterPageHandler();
    }

    protected OverviewPageHandler getOverviewPageHandler() {
        return parabankPageStore.getOverviewPageHandler();
    }

    protected OpenAccountPageHandler getOpenAccountPageHandler() {
        return parabankPageStore.getOpenAccountPageHandler();
    }
    protected AccountActivityPageHandler getAccountActivityPageHandler(){
        return parabankPageStore.getAccountActivityPageHandler();
    }
}