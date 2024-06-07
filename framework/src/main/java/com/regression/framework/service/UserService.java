package com.regression.framework.service;

import com.regression.framework.rest.client.UserClient;
import com.regression.framework.rest.request.CreateUserRequestDTO;
import com.regression.framework.rest.response.UserDTO;
import com.regression.framework.service.util.RandomService;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class UserService {
    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private final UserClient userClient;
    private final RandomService randomService;

    public UserService(final UserClient userClient, final RandomService randomService) {
        this.userClient = userClient;
        this.randomService = randomService;
    }

    public UserDTO initContextUser(final String statusString) {
        UserDTO user = new UserDTO();

        UserDTO.StatusEnum status = UserDTO.StatusEnum.valueOf(statusString);

        user.setName(randomService.getRandomString(10));
        user.setEmail(randomService.getRandomString(7) + "@gmail.com");
        user.setStatus(status);

        LOG.debug("User: {}", user);
        return user;
    }

    public Response registerUser(final UserDTO user) {

        CreateUserRequestDTO body = new CreateUserRequestDTO();

        CreateUserRequestDTO.StatusEnum status = CreateUserRequestDTO.StatusEnum.valueOf(user.getStatus().toString());

        body.setName(user.getName());
        body.setEmail(user.getEmail());
        body.setStatus(status);

        LOG.debug("User create request body: {}", body);
        return userClient.createUser(body);
    }

    public Response getUsers() {
        return userClient.getUsers();
    }


    public Response deleteUser(final Long userId) {
        return userClient.deleteUser(userId);
    }
}
