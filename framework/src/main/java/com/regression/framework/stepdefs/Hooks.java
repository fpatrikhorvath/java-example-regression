package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverInitializationListener;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends TestCore {
    private final WebDriverFactory webDriverFactory;

    public Hooks(final UserLayerContextStore userLayerContextStore,
                 final ScenarioContext scenarioContext,
                 final ParabankPageStore parabankPageStore,
                 final WebDriverFactory webDriverFactory) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
        this.webDriverFactory = webDriverFactory;
    }

    @After
    public void tearDown(final Scenario scenario) {
        if (scenario.isFailed() && WebDriverInitializationListener.isInitialized()) {
            byte[] screenshot = ((TakesScreenshot) webDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
        if (WebDriverInitializationListener.isInitialized()) {
            webDriverFactory.tearDown();
            WebDriverInitializationListener.setIsInitialized(false);
        }
    }
}
