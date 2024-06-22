package com.regression.framework.stores;

import com.regression.framework.selenium.handler.IndexPageHandler;
import com.regression.framework.selenium.handler.OverviewPageHandler;
import com.regression.framework.selenium.handler.RegisterPageHandler;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public class ParabankPageStore {
    private final IndexPageHandler indexPageHandler;
    private final RegisterPageHandler registerPageHandler;
    private final OverviewPageHandler overviewPageHandler;

    public ParabankPageStore(final IndexPageHandler loginPageHandler,
                             final RegisterPageHandler registerPageHandler,
                             final OverviewPageHandler overviewPageHandler) {
        this.indexPageHandler = loginPageHandler;
        this.registerPageHandler = registerPageHandler;
        this.overviewPageHandler = overviewPageHandler;
    }

    public IndexPageHandler getIndexPageHandler() {
        return indexPageHandler;
    }


    public RegisterPageHandler getRegisterPageHandler() {
        return registerPageHandler;
    }

    public OverviewPageHandler getOverviewPageHandler() {
        return overviewPageHandler;
    }
}
