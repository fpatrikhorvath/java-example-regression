package com.regression.framework.selenium.handlerSchemas;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public interface IOpenAccountPageHandler {
    void fillNewAccountForm(final String type);

    void sendForm();

    boolean isAccountCreated();

    void openNewAccountActivity();

}
