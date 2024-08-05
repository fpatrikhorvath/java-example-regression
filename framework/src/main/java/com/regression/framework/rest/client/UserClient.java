package com.regression.framework.rest.client;

import com.regression.framework.config.UserLayerConfig;
import com.regression.framework.rest.clientSchema.IUserClient;
import com.regression.framework.rest.request.CreateUserRequestDTO;
import com.regression.framework.rest.util.RestClient;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@ScenarioScope
public class UserClient implements IUserClient {
    private static final String CREATE_USER_PATH = "/users";
    private static final String GET_USER_PATH = "/users";
    private static final String DELETE_USER_PATH = "/users/{userId}";
    private final UserLayerConfig userLayerConfig;
    private RestClient restClient;

    public UserClient(final UserLayerConfig userLayerConfig) {
        this.userLayerConfig = userLayerConfig;
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.restClient = new RestClient(userLayerConfig.getUrl(), headers);
    }

    public Response createUser(final CreateUserRequestDTO body) {
        return restClient.POST(CREATE_USER_PATH, body);
    }


    public Response deleteUser(final Long userId) {
        final String endpoint = StringUtils.replace(DELETE_USER_PATH, "{userId}", String.valueOf(userId));
        return restClient.DELETE(endpoint);
    }

    public Response getUsers() {
        return restClient.GET(GET_USER_PATH);
    }
}
