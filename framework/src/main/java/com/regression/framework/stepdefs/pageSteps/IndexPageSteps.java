package com.regression.framework.stepdefs.pageSteps;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.selenium.model.ContextUser;
import com.regression.framework.stepdefs.TestCore;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IndexPageSteps extends TestCore {

    public IndexPageSteps(final UserLayerContextStore userLayerContextStore,
                          final ScenarioContext scenarioContext,
                          final ParabankPageStore parabankPageStore) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
    }

    @When("I log in as {word}")
    public void iLogInAs(final String identifier) {

        ContextUser user = (ContextUser) scenarioContext.getContextObject(identifier);

        getIndexPageHandler().login(user.getUsername(), user.getPassword());
    }
    @Then("verify that the user is logged in")
    public void verifyThatTheUserIsLoggedIm() {

        assertThat(getRegisterPageHandler().isAt()).isFalse();
    }

    @Then("verify that the user is not logged in")
    public void verifyThatTheUserIsNotLoggedIm() {

        assertThat(getIndexPageHandler().isAt()).isTrue();
    }
}
