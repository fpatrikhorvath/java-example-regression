package com.regression.framework.stepdefs;


import com.regression.framework.context.ScenarioContext;
import com.regression.framework.rest.response.BookDTO;
import com.regression.framework.rest.response.GenericErrorResponse;
import com.regression.framework.rest.response.UserDTO;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.testng.AssertJUnit.assertTrue;


public class BookSteps extends TestCore {
    public BookSteps(final UserLayerContextStore userLayerContextStore, final ScenarioContext scenarioContext) {
        super(userLayerContextStore, scenarioContext);
    }

    @When("(create )a new book for user {word} and store it as {word} -> {}")
    public void createANewBookForUserAndStoreItAs
            (final String userId, final String bookId, final HttpStatus httpStatus) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(userId);
        BookDTO book = getBookService().initContextBook(user.getId());

        Response response = getBookService()
        scenarioContext.storeContextObject(bookId, book);
    }


    @Then("verify that book {word} does not exist")
    public void verifyThatBookDoesNotExist(final String bookId) {
        BookDTO book = (BookDTO) scenarioContext.getContextObject(bookId);

        Response response = getBookService().getBooks(book);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        BookDTO actBook = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), book.getTitle()))
                .findFirst()
                .orElse(null);

        assertNull(actBook);
    }

    @Then("verify that book {word} exist")
    public void verifyThatBookExist(final String bookId) {
        BookDTO expBook = (BookDTO) scenarioContext.getContextObject(bookId);

        Response response = getBookService().getBooks(expBook);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(HttpStatus.OK));

        BookDTO actBook = Objects.requireNonNull(response.getBody())
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), expBook.getTitle()))
                .findFirst()
                .orElse(null);

        assert actBook != null;
        assertEquals(actBook, expBook);
    }

    @When("delete book {word} for user {word} -> {}")
    public void deleteBook(final String bookId, final String userId, final HttpStatus httpStatus) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(userId);
        BookDTO book = (BookDTO) scenarioContext.getContextObject(bookId);

        Response response = getBookService().deleteBook(user, book);
        assertTrue(RESPONSE_CODE_CHECK_MESSAGE, response.getStatusCode().isSameCodeAs(httpStatus));
    }
}
