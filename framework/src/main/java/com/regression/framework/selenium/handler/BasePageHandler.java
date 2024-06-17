package com.regression.framework.selenium.handler;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public abstract class BasePageHandler {
    protected final WebDriverWaitFactory driverWaitFactory;
    protected final WebDriverFactory driverFactory;
    protected final ParabankConfig parabankConfig;

    protected BasePageHandler(final WebDriverWaitFactory webDriverWaitFactory,
                              final WebDriverFactory driverFactory,
                              final ParabankConfig parabankConfig) {
        this.driverWaitFactory = webDriverWaitFactory;
        this.driverFactory = driverFactory;
        this.parabankConfig = parabankConfig;
    }

    protected Wait<WebDriver> defaultWaitFor() {
        return driverWaitFactory.defaultWait(driverFactory.getDriver());
    }

    protected abstract boolean isAt();

    protected abstract void goTo();
}
