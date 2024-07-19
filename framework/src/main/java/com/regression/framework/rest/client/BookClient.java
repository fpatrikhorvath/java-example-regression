package com.regression.framework.rest.client;

import com.regression.framework.config.UserLayerConfig;
import com.regression.framework.rest.request.CreateBookForUserRequestDTO;
import com.regression.framework.rest.util.RestClient;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@ScenarioScope
public class BookClient {
    private static final String GET_BOOK_PATH = "/users/{userId}/books";
    private static final String POST_BOOK_PATH = "/users/{userId}/books";
    private static final String DELETE_BOOK_PATH = "/users/{userId}/books/{bookId}";
    private final UserLayerConfig userLayerConfig;
    private final RestClient restClient;

    public BookClient(final UserLayerConfig userLayerConfig) {
        this.userLayerConfig = userLayerConfig;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        this.restClient = new RestClient(userLayerConfig.getUrl(), headers);
    }

    public Response postBook(final Long userId, final CreateBookForUserRequestDTO requestBody) {
        String endpoint = StringUtils.replace(POST_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.POST(endpoint, requestBody);
    }


    public Response deleteBook(final Long userId, final Long bookId) {
        String endpoint = StringUtils
                .replace(DELETE_BOOK_PATH, "{userId}", String.valueOf(userId))
                .replace("{bookId}", String.valueOf(bookId));
        return restClient.DELETE(endpoint);
    }

    public Response getBooksForUser(final Long userId) {
        String endpoint = StringUtils.replace(GET_BOOK_PATH, "{userId}", String.valueOf(userId));
        return restClient.GET(endpoint);
    }
}
