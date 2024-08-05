package com.regression.framework.service;

import com.regression.framework.rest.client.UserClient;
import com.regression.framework.rest.request.CreateUserRequestDTO;
import com.regression.framework.rest.response.UserDTO;
import com.regression.framework.service.util.FakerService;
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
    private final FakerService fakerService;

    public UserService(final UserClient userClient,
                       final FakerService fakerService) {
        this.userClient = userClient;
        this.fakerService = fakerService;
    }

    public UserDTO initContextUser(final String statusString) {
        final UserDTO user = new UserDTO();
        final UserDTO.StatusEnum status = UserDTO.StatusEnum.valueOf(statusString);
        user.setName(fakerService.name().fullName());
        user.setEmail(fakerService.name().username() + "@gmail.com");
        user.setStatus(status);

        LOG.debug("User: {}", user);
        return user;
    }

    public Response registerUser(final UserDTO user) {
        final CreateUserRequestDTO body = new CreateUserRequestDTO();
        final CreateUserRequestDTO.StatusEnum status = CreateUserRequestDTO.StatusEnum.valueOf(user.getStatus().toString());
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
