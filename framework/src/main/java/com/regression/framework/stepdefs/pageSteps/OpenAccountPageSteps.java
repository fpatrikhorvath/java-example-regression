package com.regression.framework.stepdefs.pageSteps;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.selenium.model.ContextAccount;
import com.regression.framework.stepdefs.TestCore;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OpenAccountPageSteps extends TestCore {

    public OpenAccountPageSteps(final UserLayerContextStore userLayerContextStore,
                                final ScenarioContext scenarioContext,
                                final ParabankPageStore parabankPageStore) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
    }

    @When("I open a new account with type of {word} and store it as {word}")
    public void iOpenANewAccountWithTypeOfAndStoreItAs(final String type, final String identifier) {
        getOverviewPageHandler().navigateToOpenNewAccount();
        getOpenAccountPageHandler().isAt();

        getOpenAccountPageHandler().fillNewAccountForm(type);
        getOpenAccountPageHandler().sendForm();
        getOpenAccountPageHandler().openNewAccountActivity();

        final ContextAccount account = getAccountActivityPageHandler().initContextAccount();
        scenarioContext.storeContextObject(identifier, account);
    }


    @Then("{word} account is created")
    public void account_aAccountIsCreated(final String identifier) {
    }
}
