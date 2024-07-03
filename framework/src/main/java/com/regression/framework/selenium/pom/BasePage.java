package com.regression.framework.selenium.pom;

import com.regression.framework.selenium.WebDriverFactory;
import io.cucumber.spring.ScenarioScope;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.support.PageFactory;

@ScenarioScope

public abstract class BasePage {
    private final WebDriverFactory driverFactory;

    protected BasePage(final WebDriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    @PostConstruct
    private void init() {
        PageFactory.initElements(driverFactory.getDriver(), this);
    }
}
