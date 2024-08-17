package com.regression.framework.selenium.handlerSchemas;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public interface IOverviewPageHandler {
    void navigateToOpenNewAccount();
}
