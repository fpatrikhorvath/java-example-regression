package com.regression.framework.selenium.pom;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@ScenarioScope
@Component
public class LoginPage extends BasePage {
    private final ParabankConfig parabankConfig;
    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement usernameInputField;
    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    private WebElement passwordInputField;

    protected LoginPage(final WebDriverFactory driverFactory,
                        final WebDriverWaitFactory driverWaitFactory,
                        final ParabankConfig parabankConfig) {
        super(driverFactory, driverWaitFactory);
        this.parabankConfig = parabankConfig;
    }

    @Override
    public boolean isAt() {
        return this.defaultWaitFor().until((driver -> usernameInputField.isDisplayed()));
    }

    @Override
    public void goTo() {
        getDriver().get(parabankConfig.getUrl());
    }

    public WebElement getUsernameInputField() {
        return usernameInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }
}
