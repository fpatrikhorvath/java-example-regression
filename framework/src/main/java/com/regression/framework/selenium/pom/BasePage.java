package com.regression.framework.selenium.pom;

import com.regression.framework.selenium.WebDriverFactory;
import io.cucumber.spring.ScenarioScope;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@ScenarioScope

public abstract class BasePage {
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    private final WebDriverFactory driverFactory;

    protected BasePage(final WebDriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    @PostConstruct
    private void init() {
        PageFactory.initElements(driverFactory.getDriver(), this);
    }

    public WebDriver getDriver() {
        return driverFactory.getDriver();
    }
}
