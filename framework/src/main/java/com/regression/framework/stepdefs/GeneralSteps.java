package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.rest.response.ResponseErrorEnum;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.testng.AssertJUnit.assertEquals;

public class GeneralSteps extends TestCore {
    private static final Logger LOG = LogManager.getLogger(GeneralSteps.class);

    public GeneralSteps(final UserLayerContextStore userLayerContextStore,
                        final ScenarioContext scenarioContext,
                        final ParabankPageStore parabankPageStore) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
    }

    @Then("the response has {} error")
    public void theResponseHasError(final ResponseErrorEnum expectedResponseMessage) {
        final ResponseErrorEnum actualResponseMessage = scenarioContext.getResponse();
        LOG.info("Error response: {}", actualResponseMessage);
        assertEquals(expectedResponseMessage, actualResponseMessage);
    }
}
