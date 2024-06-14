package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverInitializationListener;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks extends TestCore {
    private final WebDriverFactory webDriverFactory;

    public Hooks(final UserLayerContextStore userLayerContextStore,
                 final ScenarioContext scenarioContext,
                 final ParabankPageStore parabankPageStore,
                 final WebDriverInitializationListener webDriverInitializationListener, final WebDriverFactory webDriverFactory) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
        this.webDriverFactory = webDriverFactory;
    }

    @After
    public void tearDown(final Scenario scenario) {
        if (WebDriverInitializationListener.isInitialized()) {
            webDriverFactory.tearDown();
            WebDriverInitializationListener.setIsInitialized(false);
        }
    }
}
