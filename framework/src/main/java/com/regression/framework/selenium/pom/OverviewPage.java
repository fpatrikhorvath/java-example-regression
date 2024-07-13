package com.regression.framework.selenium.pom;

import com.regression.framework.selenium.WebDriverFactory;
import io.cucumber.spring.ScenarioScope;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@ScenarioScope
@Component
public class OverviewPage extends BasePage {
    @FindBy(how = How.ID, using = "accountTable")
    private WebElement accountTable;
    @FindBy(how = How.XPATH, using = "//a[@href='openaccount.htm']")
    private WebElement openNewAccountButton;

    protected OverviewPage(final WebDriverFactory driverFactory) {
        super(driverFactory);
    }

    public WebElement getAccountTable() {
        return accountTable;
    }

    public WebElement getOpenNewAccountButton() {
        return openNewAccountButton;
    }
}
