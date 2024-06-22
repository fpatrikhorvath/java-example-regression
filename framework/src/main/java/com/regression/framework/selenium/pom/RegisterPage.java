package com.regression.framework.selenium.pom;

import com.regression.framework.selenium.WebDriverFactory;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@ScenarioScope
@Component
public class RegisterPage extends BasePage {
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

    @FindBy(how = How.XPATH, using = "//a[@href='logout.htm']")
    private WebElement logoutButton;

    @FindBy(how = How.XPATH, using = "//div[@id='rightPanel']/h1")
    private WebElement welcomeTitle;

    protected RegisterPage(final WebDriverFactory driverFactory) {
        super(driverFactory);
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

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getWelcomeTitle() {
        return welcomeTitle;
    }
}
