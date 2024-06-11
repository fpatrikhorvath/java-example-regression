package com.regression.framework.selenium.handler;

import com.regression.framework.selenium.pom.LoginPage;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public class LoginPageHandler {

    private final LoginPage loginPage;

    public LoginPageHandler(final LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void open() {
        loginPage.goTo();
        loginPage.isAt();
    }

    public void login(final String username, final String password) {
    }
}
