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
        getRegisterPageHandler().goTo();
        getRegisterPageHandler().isAt();
    }

    @Given("I sign up with correct credentials and store it as {word}")
    public void iSignUpWithCorrectCredentialsAndStoreItAs(final String identifier) {
        final ContextUser user = getRegisterPageHandler().initContextUser();
        getRegisterPageHandler().fillRegisterForm(user);
        scenarioContext.storeContextObject(identifier, user);
    }

    @Given("I sign up without {word} and store it as {word}")
    public void iSignUpWithoutAndStoreItAs(final String without, final String identifier) {
        final ContextUser user = getRegisterPageHandler().initContextUser();

        switch (without) {
            case "username" -> user.setUsername("");
            case "password" -> user.setPassword("");
            case "city" -> user.setCity("");
            case "phone" -> user.setPhone("");
            default -> throw new RuntimeException("This option is not implemented for incorrect registration!");
        }

        getRegisterPageHandler().fillRegisterForm(user);
        scenarioContext.storeContextObject(identifier, user);
    }

    @Given("I log out")
    public void iLogOut() {
        getRegisterPageHandler().logOut();
    }

    @Then("verify that the user {word} is registered")
    public void verifyThatTheUserIsRegistered(final String identifier) {
        final ContextUser user = (ContextUser) scenarioContext.getContextObject(identifier);
        final String expectedWelcomeMessage = "Welcome ".concat(user.getUsername());
        assertThat(getRegisterPageHandler().getWelcomeMessage()).isEqualTo(expectedWelcomeMessage);
    }

    @Then("verify that the user is not registered")
    public void verifyThatTheUserIsNotRegistered() {
        assertThat(getRegisterPageHandler().isLogoutButtonVisible()).isFalse();
    }
}
