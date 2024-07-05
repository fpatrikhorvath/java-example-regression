package com.regression.framework.selenium.pom;

import com.regression.framework.selenium.WebDriverFactory;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@ScenarioScope
@Component
public class AccountActivityPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@id=\"accountId\"]")
    private WebElement accountIdentifier;
    @FindBy(how = How.XPATH, using = "//*[@id=\"accountType\"]")
    private WebElement accountType;
    @FindBy(how = How.XPATH, using = "//*[@id=\"balance\"]")
    private WebElement balance;
    @FindBy(how = How.XPATH, using = "//*[@id=\"available\"]")
    private WebElement available;
    protected AccountActivityPage(final WebDriverFactory driverFactory) {
        super(driverFactory);
    }

    public WebElement getAccountIdentifier() {
        return accountIdentifier;
    }

    public WebElement getAccountType() {
        return accountType;
    }

    public WebElement getBalance() {
        return balance;
    }

    public WebElement getAvailable() {
        return available;
    }
}
