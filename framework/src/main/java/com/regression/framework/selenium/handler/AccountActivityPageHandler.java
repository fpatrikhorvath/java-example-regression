package com.regression.framework.selenium.handler;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import com.regression.framework.selenium.handlerSchemas.IAccountActivityPageHandler;
import com.regression.framework.selenium.model.ContextAccount;
import com.regression.framework.selenium.pom.AccountActivityPage;
import com.regression.framework.service.util.ParserService;
import io.cucumber.spring.ScenarioScope;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@ScenarioScope
@Service
public class AccountActivityPageHandler extends BasePageHandler implements IAccountActivityPageHandler {
    private static final Logger logger = LogManager.getLogger(AccountActivityPageHandler.class);
    private final String PAGE_NAME = "openaccount";
    private final AccountActivityPage activityPage;
    private final ParserService parserService;

    protected AccountActivityPageHandler(final WebDriverWaitFactory webDriverWaitFactory,
                                         final WebDriverFactory driverFactory,
                                         final ParabankConfig parabankConfig,
                                         final AccountActivityPage activityPage,
                                         final ParserService parserService) {
        super(webDriverWaitFactory, driverFactory, parabankConfig);
        this.activityPage = activityPage;
        this.parserService = parserService;
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
        account.setBalance(parserService.parseDollar(activityPage.getBalance().getText()));
        account.setAvailable(parserService.parseDollar(activityPage.getAvailable().getText()));
        logger.debug("Account: {}", account);
        return account;
    }
}
