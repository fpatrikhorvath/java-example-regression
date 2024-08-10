package com.regression.framework.selenium.handler;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import com.regression.framework.selenium.handlerSchemas.IIndexpagehandler;
import com.regression.framework.selenium.pom.IndexPage;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@ScenarioScope
@Service
public class IndexPageHandler extends BasePageHandler implements IIndexpagehandler {
    private final String PAGE_NAME = "index";
    private final IndexPage indexPage;

    protected IndexPageHandler(final WebDriverWaitFactory webDriverWaitFactory,
                               final WebDriverFactory driverFactory,
                               final IndexPage indexPage,
                               final ParabankConfig parabankConfig) {
        super(webDriverWaitFactory, driverFactory, parabankConfig);
        this.indexPage = indexPage;
    }

    public void navigateTo(final String pageName) {
        switch (pageName) {
            case "forgotPassword" -> indexPage.getForgotPasswordButton().click();
            case "register" -> indexPage.getRegisterButton().click();
            default -> throw new RuntimeException("The given page name is not an option!");
        }
    }

    public void login(final String username, final String password) {
        indexPage.getUsernameInputField().sendKeys(username);
        indexPage.getPasswordInputField().sendKeys(password);
        indexPage.getLoginButton().click();
    }

    @Override
    public boolean isAt() {
        return this.defaultWaitFor().until((driver -> indexPage.getUsernameInputField().isDisplayed()));
    }

    @Override
    public void goTo() {
        final String url = StringUtils.replace(parabankConfig.getUrl(), "{pageName}", PAGE_NAME);
        driverFactory.getDriver().get(url);
    }
}
