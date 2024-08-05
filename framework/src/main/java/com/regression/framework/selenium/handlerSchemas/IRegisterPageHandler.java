package com.regression.framework.selenium.handlerSchemas;

import com.regression.framework.selenium.model.ContextUser;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public interface IRegisterPageHandler {
    ContextUser initContextUser();

    void fillRegisterForm(final ContextUser user);

    void logOut();

    boolean isLogoutButtonVisible();

    String getWelcomeMessage();
}
