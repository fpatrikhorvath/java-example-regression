package com.regression.framework.selenium.pom;

import com.regression.framework.selenium.WebDriverFactory;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@ScenarioScope
@Component
public class OpenAccountPage extends BasePage {
    @FindBy(how = How.ID, using = "type")
    private WebElement accountTypeDropdown;

    @FindBy(how = How.ID, using = "fromAccountId")
    private WebElement fromAccountIdDropdown;

    @FindBy(how = How.XPATH, using = "//input[@value='Open New Account']")
    private WebElement openNewAccountButton;
    @FindBy(how = How.ID, using = "newAccountId")
    private WebElement newAccountIdButton;
    protected OpenAccountPage(final WebDriverFactory driverFactory) {
        super(driverFactory);
    }

    public WebElement getAccountTypeDropdown() {
        return accountTypeDropdown;
    }

    public WebElement getFromAccountIdDropdown() {
        return fromAccountIdDropdown;
    }

    public WebElement getOpenNewAccountButton() {
        return openNewAccountButton;
    }

    public WebElement getNewAccountIdButton() {
        return newAccountIdButton;
    }
}
