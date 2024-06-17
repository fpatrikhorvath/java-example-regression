package com.regression.framework.stepdefs.pageSteps;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.selenium.model.ContextUser;
import com.regression.framework.stepdefs.TestCore;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegisterPageSteps extends TestCore {

    public RegisterPageSteps(final UserLayerContextStore userLayerContextStore,
                             final ScenarioContext scenarioContext,
                             final ParabankPageStore parabankPageStore) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
    }

    @Given("I am on the register page")
    public void iAmOnTheRegisterPage() {
        getRegisterPageHandler().open();

    }

    @Given("I sign up with correct credentials and store it as {word}")
    public void iSignUpWithCorrectCredentialsAndStoreItAs(final String identifier) {
        ContextUser user = getRegisterPageHandler().initContextUser();
        getRegisterPageHandler().register(user);
        scenarioContext.storeContextObject(identifier, user);
    }

    @Given("I sign up without {word} and store it as {word}")
    public void iSignUpWithoutAndStoreItAs(final String without, final String identifier) {
        ContextUser user = getRegisterPageHandler().initContextUser();

        switch (without) {
            case "username" -> user.setUsername("");
            case "password" -> user.setPassword("");
            default -> throw new RuntimeException("This option is not implemented for incorrect registration!");
        }

        getRegisterPageHandler().register(user);
        scenarioContext.storeContextObject(identifier, user);
    }

    @Then("verify that the user is logged in")
    public void verifyThatTheUserIsLoggedIm() {
        assertThat(getRegisterPageHandler().isLoggedIn()).isTrue();
    }

    @Then("verify that the user is not logged in")
    public void verifyThatTheUserIsNotLoggedIm() {
        assertThat(getIndexPageHandler().isLoggedOut()).isTrue();
    }

    @Given("I log out")
    public void iLogOut() {
        getRegisterPageHandler().logOut();
    }
}