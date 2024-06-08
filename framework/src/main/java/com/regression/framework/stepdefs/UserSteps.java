package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.rest.response.UserDTO;
import com.regression.framework.service.util.MapperService;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public class UserSteps extends TestCore {
    private final MapperService mapperService;

    public UserSteps(final UserLayerContextStore userLayerContextStore,
                     final ScenarioContext scenarioContext,
                     final MapperService mapperService) {
        super(userLayerContextStore, scenarioContext);
        this.mapperService = mapperService;
    }

    @Given("(create )a new user of status {word} and store it as {word} -> {int}")
    public void createANewUserOfTypeAndStoreItAs
            (final String statusString, final String contextId, final int responseCode) {

        UserDTO user = getUserService().initContextUser(statusString);

        Response response = getUserService().registerUser(user);
        assertThat(response.getStatusCode()).withFailMessage(RESPONSE_CODE_CHECK_MESSAGE)
                .isEqualTo(responseCode);

        if (201 == response.getStatusCode()) {
            UserDTO responseUser = mapperService.mutateObject(response, UserDTO.class);
            user.setId(responseUser.getId());
        }

        scenarioContext.storeContextObject(contextId, user);
    }

    @Then("verify that user {word} exists")
    public void verifyThatUserExists(final String contextId) {
        UserDTO expUser = (UserDTO) scenarioContext.getContextObject(contextId);

        Response response = getUserService().getUsers();
        assertThat(response.getStatusCode()).withFailMessage(RESPONSE_CODE_CHECK_MESSAGE)
                .isEqualTo(HttpStatus.OK.value());

        List<UserDTO> userList = mapperService.mutateObjectList(response, UserDTO.class);

        UserDTO actUser = Objects.requireNonNull(userList)
                .stream()
                .filter(u -> Objects.equals(u.getName(), expUser.getName()))
                .findFirst()
                .orElse(null);

        assertEquals(actUser, expUser);
    }

    @When("delete user {word} -> {int}")
    public void deleteUser(final String contextId, final int responseCode) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(contextId);

        Response response = getUserService().deleteUser(user.getId());
        assertThat(response.getStatusCode()).withFailMessage(RESPONSE_CODE_CHECK_MESSAGE)
                .isEqualTo(responseCode);

        if (204 != response.getStatusCode()) {
            scenarioContext.storeResponse(response);
        }
    }

    @Then("verify that user {word} does not exist")
    public void verifyThatUserDoesNotExist(final String contextId) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(contextId);

        Response response = getUserService().getUsers();
        assertThat(response.getStatusCode()).withFailMessage(RESPONSE_CODE_CHECK_MESSAGE)
                .isEqualTo(HttpStatus.OK.value());

        List<UserDTO> userList = mapperService.mutateObjectList(response, UserDTO.class);

        UserDTO actUser = Objects.requireNonNull(userList)
                .stream()
                .filter(u -> Objects.equals(u.getName(), user.getName()))
                .findFirst()
                .orElse(null);

        assertNull(actUser);
    }
}
