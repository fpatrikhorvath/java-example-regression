package com.regression.framework.rest.clientSchema;

import com.regression.framework.rest.request.CreateBookForUserRequestDTO;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public interface IBookClient {
    Response postBook(final Long userId, final CreateBookForUserRequestDTO requestBody);

    Response deleteBook(final Long userId, final Long bookId);

    Response getBooksForUser(final Long userId);
}
