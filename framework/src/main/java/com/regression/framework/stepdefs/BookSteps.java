package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.rest.response.BookDTO;
import com.regression.framework.rest.response.UserDTO;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


public class BookSteps extends TestCore {
    public BookSteps(final UserLayerContextStore userLayerContextStore, final ScenarioContext scenarioContext) {
        super(userLayerContextStore, scenarioContext);
    }

    @When("(create )a new book for user {word} and store it as {word} -> {}")
    public void createANewBookForUserAndStoreItAs
            (final String userId, final String bookId, final HttpStatus httpStatus) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(userId);
        BookDTO book = getBookService().initContextBook(user.getId());

        Response response = getBookService().registerBook(book);
        assertThat(response.getStatusCode()).
                withFailMessage(RESPONSE_CODE_CHECK_MESSAGE)
                .isEqualTo(httpStatus.value());

        if (httpStatus.value() == HttpStatus.CREATED.value()){
            BookDTO responseBook = mutateObject(response, BookDTO.class);
            book.setId(responseBook.getId());
        } else {
            scenarioContext.storeResponse(response);
        }

        scenarioContext.storeContextObject(bookId, book);
    }


    @Then("verify that book {word} does not exist")
    public void verifyThatBookDoesNotExist(final String bookId) {
        BookDTO book = (BookDTO) scenarioContext.getContextObject(bookId);

        Response response = getBookService().getBooks(book);
        assertThat(response.getStatusCode()).withFailMessage(RESPONSE_CODE_CHECK_MESSAGE).isEqualTo(HttpStatus.OK);

        List<BookDTO> bookDTOList = mutateObjectList(response, BookDTO.class);
        BookDTO actBook = bookDTOList
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), book.getTitle()))
                .findFirst()
                .orElse(null);

        assert actBook == null;
    }

    @Then("verify that book {word} exist")
    public void verifyThatBookExist(final String bookId) {
        BookDTO expBook = (BookDTO) scenarioContext.getContextObject(bookId);

        Response response = getBookService().getBooks(expBook);
        assertThat(response.getStatusCode()).withFailMessage(RESPONSE_CODE_CHECK_MESSAGE)
                .isEqualTo(HttpStatus.OK.value());

        List<BookDTO> bookDTOList = mutateObjectList(response, BookDTO.class);
        BookDTO actBook = bookDTOList
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), expBook.getTitle()))
                .findFirst()
                .orElse(null);

        assert actBook != null;
        assertThat(actBook).isEqualTo(expBook);
    }

    @When("delete book {word} for user {word} -> {}")
    public void deleteBook(final String bookId, final String userId, final HttpStatus httpStatus) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(userId);
        BookDTO book = (BookDTO) scenarioContext.getContextObject(bookId);

        Response response = getBookService().deleteBook(user, book);
        assertThat(response.getStatusCode()).withFailMessage(RESPONSE_CODE_CHECK_MESSAGE)
                .isEqualTo(httpStatus.value());
    }
}
