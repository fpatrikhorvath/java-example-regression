package com.regression.framework.rest.clientSchema;

import com.regression.framework.rest.request.CreateUserRequestDTO;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public interface IUserClient {
    Response createUser(final CreateUserRequestDTO body);

    Response deleteUser(final Long userId);

    Response getUsers();
}
