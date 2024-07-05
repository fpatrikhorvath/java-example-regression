package com.regression.framework.selenium.handler;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import com.regression.framework.selenium.model.ContextAccount;
import com.regression.framework.selenium.pom.AccountActivityPage;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@ScenarioScope
@Service
public class AccountActivityPageHandler extends BasePageHandler {
    private static final Logger logger = LogManager.getLogger(AccountActivityPageHandler.class);
    private final String PAGE_NAME = "openaccount";
    private final AccountActivityPage activityPage;

    protected AccountActivityPageHandler(final WebDriverWaitFactory webDriverWaitFactory,
                                         final WebDriverFactory driverFactory,
                                         final ParabankConfig parabankConfig,
                                         final AccountActivityPage activityPage) {
        super(webDriverWaitFactory, driverFactory, parabankConfig);
        this.activityPage = activityPage;
    }

    @Override
    public boolean isAt() {
        return this.defaultWaitFor().until((driver -> activityPage.getAvailable().isDisplayed()));
    }

    @Override
    public void goTo() {
        String url = StringUtils.replace(parabankConfig.getUrl(), "{pageName}", PAGE_NAME);
        driverFactory.getDriver().get(url);
    }

    public ContextAccount initContextAccount() {
        ContextAccount account = new ContextAccount();
        account.setId(Long.parseLong(activityPage.getAccountIdentifier().getText()));
        //TODO: service
        account.setBalance(Double.parseDouble(activityPage.getBalance().getText().replace("$","")));
        account.setAvailable(Double.parseDouble(activityPage.getBalance().getText().replace("$","")));
        logger.debug("Account: {}", account);
        return account;
    }

}
