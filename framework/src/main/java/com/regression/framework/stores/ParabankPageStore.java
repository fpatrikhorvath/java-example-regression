package com.regression.framework.stores;

import com.regression.framework.selenium.handler.IndexPageHandler;
import com.regression.framework.selenium.handler.OpenAccountPageHandler;
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

    private final OpenAccountPageHandler openAccountPageHandler;
    public ParabankPageStore(final IndexPageHandler loginPageHandler,
                             final RegisterPageHandler registerPageHandler,
                             final OverviewPageHandler overviewPageHandler,
                             final OpenAccountPageHandler openAccountPageHandler) {
        this.indexPageHandler = loginPageHandler;
        this.registerPageHandler = registerPageHandler;
        this.overviewPageHandler = overviewPageHandler;
        this.openAccountPageHandler = openAccountPageHandler;
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

    public OpenAccountPageHandler getOpenAccountPageHandler() {
        return openAccountPageHandler;
    }
}
