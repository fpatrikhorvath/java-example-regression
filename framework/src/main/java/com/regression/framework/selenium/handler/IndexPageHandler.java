package com.regression.framework.selenium.handler;

import com.regression.framework.selenium.pom.IndexPage;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public class IndexPageHandler {

    private final IndexPage indexPage;

    public IndexPageHandler(final IndexPage loginPage) {
        this.indexPage = loginPage;
    }

    public void open() {
        indexPage.goTo();
        indexPage.isAt();
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
    }
}
