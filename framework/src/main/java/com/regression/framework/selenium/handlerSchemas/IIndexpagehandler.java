package com.regression.framework.selenium.handlerSchemas;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public interface IIndexpagehandler {
    void navigateTo(final String pageName);

    void login(final String username, final String password);

}
