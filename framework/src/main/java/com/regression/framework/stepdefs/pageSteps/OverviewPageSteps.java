package com.regression.framework.stepdefs.pageSteps;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.stepdefs.TestCore;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OverviewPageSteps extends TestCore {

    public OverviewPageSteps(final UserLayerContextStore userLayerContextStore,
                             final ScenarioContext scenarioContext,
                             final ParabankPageStore parabankPageStore) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
    }

    @Then("verify that the user is logged in")
    public void verifyThatTheUserIsLoggedIm() {
        assertThat(getOverviewPage().isAt()).isTrue();
    }
}
