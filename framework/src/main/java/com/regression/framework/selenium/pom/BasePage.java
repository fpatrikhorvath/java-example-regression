package com.regression.framework.selenium.pom;

import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import io.cucumber.spring.ScenarioScope;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

@ScenarioScope

public abstract class BasePage {
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    protected final WebDriverFactory driverFactory;
    protected final WebDriverWaitFactory driverWaitFactory;

    protected BasePage(final WebDriverFactory driverFactory,
                       final WebDriverWaitFactory driverWaitFactory) {
        this.driverFactory = driverFactory;
        this.driverWaitFactory = driverWaitFactory;
    }

    @PostConstruct
    private void init() {
        PageFactory.initElements(driverFactory.getDriver(), this);
    }

    public WebDriver getDriver() {
        return driverFactory.getDriver();
    }

    public abstract boolean isAt();

    public abstract void goTo();

    public Wait<WebDriver> defaultWaitFor() {
        return driverWaitFactory.defaultWait(driverFactory.getDriver());
    }
}
