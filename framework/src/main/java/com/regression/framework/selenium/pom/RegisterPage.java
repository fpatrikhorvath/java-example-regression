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
public class RegisterPage extends BasePage {
    private final ParabankConfig parabankConfig;
    private final String PAGE_NAME = "register";
    @FindBy(how = How.ID, using = "customer.firstName")
    private WebElement firstNameInputField;
    @FindBy(how = How.ID, using = "customer.lastName")
    private WebElement lastNameInputField;
    @FindBy(how = How.ID, using = "customer.address.street")
    private WebElement streetInputField;
    @FindBy(how = How.ID, using = "customer.address.city")
    private WebElement cityInputField;
    @FindBy(how = How.ID, using = "customer.address.state")
    private WebElement stateInputField;
    @FindBy(how = How.ID, using = "customer.address.zipCode")
    private WebElement zipCodeInputField;
    @FindBy(how = How.ID, using = "customer.phoneNumber")
    private WebElement phoneInputField;
    @FindBy(how = How.ID, using = "customer.ssn")
    private WebElement ssnInputField;
    @FindBy(how = How.ID, using = "customer.username")
    private WebElement usernameInputField;
    @FindBy(how = How.ID, using = "customer.password")
    private WebElement passwordInputField;
    @FindBy(how = How.ID, using = "repeatedPassword")
    private WebElement passwordAgainInputField;

    @FindBy(how = How.XPATH, using = "//input[@value='Register']")
    private WebElement registerButton;

    protected RegisterPage(final WebDriverFactory driverFactory,
                           final WebDriverWaitFactory driverWaitFactory,
                           final ParabankConfig parabankConfig) {
        super(driverFactory, driverWaitFactory);
        this.parabankConfig = parabankConfig;
    }

    @Override
    public boolean isAt() {
        return this.defaultWaitFor().until((driver -> firstNameInputField.isDisplayed()));
    }

    @Override
    public void goTo() {
        String url = StringUtils.replace(parabankConfig.getUrl(), "{pageName}", PAGE_NAME);
        getDriver().get(url);
    }

    public WebElement getFirstNameInputField() {
        return firstNameInputField;
    }

    public WebElement getLastNameInputField() {
        return lastNameInputField;
    }

    public WebElement getStreetInputField() {
        return streetInputField;
    }

    public WebElement getCityInputField() {
        return cityInputField;
    }

    public WebElement getStateInputField() {
        return stateInputField;
    }

    public WebElement getZipCodeInputField() {
        return zipCodeInputField;
    }

    public WebElement getPhoneInputField() {
        return phoneInputField;
    }

    public WebElement getSsnInputField() {
        return ssnInputField;
    }

    public WebElement getUsernameInputField() {
        return usernameInputField;
    }

    public WebElement getPasswordInputField() {
        return passwordInputField;
    }

    public WebElement getPasswordAgainInputField() {
        return passwordAgainInputField;
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }
}
