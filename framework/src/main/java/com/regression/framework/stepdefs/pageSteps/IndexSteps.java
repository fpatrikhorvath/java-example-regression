package com.regression.framework.stepdefs.pageSteps;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.service.util.MapperService;
import com.regression.framework.stepdefs.TestCore;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;

public class IndexSteps extends TestCore {
    private final MapperService mapperService;

    public IndexSteps(final UserLayerContextStore userLayerContextStore,
                      final ScenarioContext scenarioContext,
                      final ParabankPageStore parabankPageStore,
                      final MapperService mapperService) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
        this.mapperService = mapperService;
    }
}
