package com.regression.framework.selenium.handler;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import com.regression.framework.selenium.pom.OpenAccountPage;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@ScenarioScope
@Service
public class OpenAccountPageHandler extends BasePageHandler {
    private final String PAGE_NAME = "openaccount";
    private final OpenAccountPage openAccountPage;

    protected OpenAccountPageHandler(final WebDriverWaitFactory webDriverWaitFactory,
                                     final WebDriverFactory driverFactory,
                                     final ParabankConfig parabankConfig,
                                     final OpenAccountPage openAccountPage) {
        super(webDriverWaitFactory, driverFactory, parabankConfig);
        this.openAccountPage = openAccountPage;
    }

    @Override
    public boolean isAt() {
        return this.defaultWaitFor().until((driver -> openAccountPage.getAccountTypeDropdown().isDisplayed()));
    }

    @Override
    public void goTo() {
        String url = StringUtils.replace(parabankConfig.getUrl(), "{pageName}", PAGE_NAME);
        driverFactory.getDriver().get(url);
    }

    public void fillNewAccountForm(final String type) {
        Select select = new Select(openAccountPage.getAccountTypeDropdown());
        switch (type) {
            case "checking" -> select.selectByVisibleText("CHECKING");
            case "savings" -> select.selectByVisibleText("SAVINGS");
            default -> throw new RuntimeException("Not an account type parameter");
        }
    }

    public void sendForm() {
        driverWaitFactory.delay(2);
        openAccountPage.getOpenNewAccountButton().click();
    }

    public boolean isAccountCreated() {
        return this.defaultWaitFor().until((driver -> openAccountPage.getNewAccountIdButton().isDisplayed()));
    }

    public long getNewAccountId() {
        isAccountCreated();
        return Long.parseLong(openAccountPage.getNewAccountIdButton().getText());
    }
}
