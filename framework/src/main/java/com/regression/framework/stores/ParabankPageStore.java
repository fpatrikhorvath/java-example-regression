package com.regression.framework.stores;

import com.regression.framework.selenium.handler.LoginPageHandler;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public class ParabankPageStore {
    private final LoginPageHandler loginPageHandler;

    public ParabankPageStore(final LoginPageHandler loginPageHandler) {
        this.loginPageHandler = loginPageHandler;
    }

    public LoginPageHandler getLoginPageHandler() {
        return loginPageHandler;
    }
}
