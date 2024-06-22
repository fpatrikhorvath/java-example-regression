package com.regression.framework.selenium.handler;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import com.regression.framework.selenium.pom.OverviewPage;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public class OverviewPageHandler extends BasePageHandler {
    private static final Logger LOG = LogManager.getLogger(OverviewPageHandler.class);
    private final String PAGE_NAME = "register";
    private final OverviewPage overviewPage;

    protected OverviewPageHandler(final WebDriverWaitFactory webDriverWaitFactory,
                                  final WebDriverFactory driverFactory,
                                  final ParabankConfig parabankConfig,
                                  final OverviewPage overviewPage) {
        super(webDriverWaitFactory, driverFactory, parabankConfig);
        this.overviewPage = overviewPage;
    }


    @Override
    public boolean isAt() {
        return this.defaultWaitFor().until((driver -> overviewPage.getAccountTable().isDisplayed()));
    }

    @Override
    public void goTo() {
        throw new RuntimeException("Not implemented yet");
    }

}
