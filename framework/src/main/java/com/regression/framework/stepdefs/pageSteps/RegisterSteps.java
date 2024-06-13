package com.regression.framework.stepdefs.pageSteps;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.selenium.model.ContextUser;
import com.regression.framework.service.util.MapperService;
import com.regression.framework.stepdefs.TestCore;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RegisterSteps extends TestCore {

    public RegisterSteps(final UserLayerContextStore userLayerContextStore,
                         final ScenarioContext scenarioContext,
                         final ParabankPageStore parabankPageStore) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
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

    @Then("verify that the registration was successful")
    public void verifyThatTheRegistrationWasSuccessful() {

    }
}
