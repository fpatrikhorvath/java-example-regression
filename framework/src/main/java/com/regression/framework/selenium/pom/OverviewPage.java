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
public class OverviewPage extends BasePage {
    private final ParabankConfig parabankConfig;
    private final String PAGE_NAME = "overview";
    @FindBy(how = How.ID, using = "accountTable")
    private WebElement accountTable;


    protected OverviewPage(final WebDriverFactory driverFactory,
                           final WebDriverWaitFactory driverWaitFactory,
                           final ParabankConfig parabankConfig) {
        super(driverFactory, driverWaitFactory);
        this.parabankConfig = parabankConfig;
    }

    @Override
    public boolean isAt() {
        return this.defaultWaitFor().until((driver -> accountTable.isDisplayed()));
    }

    @Override
    public void goTo() {
        String url = StringUtils.replace(parabankConfig.getUrl(), "{pageName}", PAGE_NAME);
        getDriver().get(url);
    }
}
