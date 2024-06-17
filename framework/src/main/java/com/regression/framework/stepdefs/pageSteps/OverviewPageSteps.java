package com.regression.framework.stepdefs.pageSteps;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.stepdefs.TestCore;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;

public class OverviewPageSteps extends TestCore {

    public OverviewPageSteps(final UserLayerContextStore userLayerContextStore,
                             final ScenarioContext scenarioContext,
                             final ParabankPageStore parabankPageStore) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
    }

}
