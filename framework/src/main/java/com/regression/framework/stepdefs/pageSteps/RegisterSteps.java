package com.regression.framework.stepdefs.pageSteps;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.selenium.model.ContextUser;
import com.regression.framework.service.util.MapperService;
import com.regression.framework.stepdefs.TestCore;
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

    @Given("I am on the register page")
    public void imAmOnTheRegisterPage() {
        getRegisterPageHandler().open();

    }

    @Given("I sign up with correct credentials and store it as {word}")
    public void iSignUpWithCorrectCredentialsAndStoreItAs(final String identifier) {
        ContextUser user = getRegisterPageHandler().initContextUser();
        getRegisterPageHandler().register(user);
        scenarioContext.storeContextObject(identifier, user);
    }
}
