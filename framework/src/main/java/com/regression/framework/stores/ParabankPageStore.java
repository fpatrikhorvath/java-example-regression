package com.regression.framework.stores;

import com.regression.framework.selenium.handler.*;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@ScenarioScope
@Service
public class ParabankPageStore {
    private final IndexPageHandler indexPageHandler;
    private final RegisterPageHandler registerPageHandler;
    private final OverviewPageHandler overviewPageHandler;
    private final AccountActivityPageHandler accountActivityPageHandler;
    private final OpenAccountPageHandler openAccountPageHandler;
    public ParabankPageStore(final IndexPageHandler loginPageHandler,
                             final RegisterPageHandler registerPageHandler,
                             final OverviewPageHandler overviewPageHandler,
                             final AccountActivityPageHandler accountActivityPageHandler,
                             final OpenAccountPageHandler openAccountPageHandler) {
        this.indexPageHandler = loginPageHandler;
        this.registerPageHandler = registerPageHandler;
        this.overviewPageHandler = overviewPageHandler;
        this.accountActivityPageHandler = accountActivityPageHandler;
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

    public AccountActivityPageHandler getAccountActivityPageHandler() {
        return accountActivityPageHandler;
    }
}
