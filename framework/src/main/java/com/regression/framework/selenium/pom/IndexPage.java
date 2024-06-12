package com.regression.framework.selenium.pom;

import com.regression.framework.config.ParabankConfig;
import com.regression.framework.selenium.WebDriverFactory;
import com.regression.framework.selenium.WebDriverWaitFactory;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@ScenarioScope
@Component
public class IndexPage extends BasePage {
    private final ParabankConfig parabankConfig;
    private final String PAGE_NAME = "index";
    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement usernameInputField;
    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    private WebElement passwordInputField;
    @FindBy(how = How.XPATH, using = "//a[@href='lookup.htm']")
    private WebElement forgotPasswordButton;
    @FindBy(how = How.XPATH, using = "//a[@href='register.htm']")
    private WebElement registerButton;

    protected IndexPage(final WebDriverFactory driverFactory,
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
        String url = StringUtils.replace(parabankConfig.getUrl(), "{pageName}", PAGE_NAME);
        getDriver().get(url);
    }

    public WebElement getUsernameInputField() {
        return usernameInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getForgotPasswordButton() {
        return forgotPasswordButton;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }
}
