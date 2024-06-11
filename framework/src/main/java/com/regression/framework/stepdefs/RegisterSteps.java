package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.service.util.MapperService;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Given;

public class RegisterSteps extends TestCore {
    private final MapperService mapperService;

    public RegisterSteps(final UserLayerContextStore userLayerContextStore,
                         final ScenarioContext scenarioContext,
                         final ParabankPageStore parabankPageStore,
                         final MapperService mapperService) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
        this.mapperService = mapperService;
    }

    @Given("the opened login page")
    public void theOpenedLoginPage() {
        getLoginPageHandler().open();
    }
}
