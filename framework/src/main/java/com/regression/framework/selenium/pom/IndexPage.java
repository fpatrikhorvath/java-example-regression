package com.regression.framework.selenium.pom;

import com.regression.framework.selenium.WebDriverFactory;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@ScenarioScope
@Component
public class IndexPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement usernameInputField;
    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    private WebElement passwordInputField;
    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private WebElement loginButton;
    @FindBy(how = How.XPATH, using = "//a[@href='lookup.htm']")
    private WebElement forgotPasswordButton;
    @FindBy(how = How.XPATH, using = "//a[@href='register.htm']")
    private WebElement registerButton;

    protected IndexPage(final WebDriverFactory driverFactory) {
        super(driverFactory);
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

    public WebElement getLoginButton() {
        return loginButton;
    }
}
