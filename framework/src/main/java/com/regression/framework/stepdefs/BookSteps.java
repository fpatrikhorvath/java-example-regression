package com.regression.framework.stepdefs;

import com.regression.framework.context.ScenarioContext;
import com.regression.framework.rest.response.BookDTO;
import com.regression.framework.rest.response.UserDTO;
import com.regression.framework.service.util.MapperService;
import com.regression.framework.stores.ParabankPageStore;
import com.regression.framework.stores.UserLayerContextStore;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class BookSteps extends TestCore {
    private final MapperService mapperService;


    public BookSteps(final UserLayerContextStore userLayerContextStore,
                     final ScenarioContext scenarioContext,
                     final ParabankPageStore parabankPageStore,
                     final MapperService mapperService) {
        super(userLayerContextStore, scenarioContext, parabankPageStore);
        this.mapperService = mapperService;
    }

    @When("(create )a new book for user {word} and store it as {word} -> {int}")
    public void createANewBookForUserAndStoreItAs
            (final String userId, final String bookId, final int responseCode) {

        UserDTO user = (UserDTO) scenarioContext.getContextObject(userId);
        BookDTO book = getBookService().initContextBook(user.getId());

        Response response = getBookService().registerBook(book);
        assertThat(response.getStatusCode()).isEqualTo(responseCode);

        if (201 == responseCode) {
            BookDTO responseBook = mapperService.mutateObject(response, BookDTO.class);
            book.setId(responseBook.getId());

        } else {
            scenarioContext.storeErrorResponse(response);

        }
        scenarioContext.storeContextObject(bookId, book);
    }


    @Then("verify that book {word} does not exist")
    public void verifyThatBookDoesNotExist(final String bookId) {
        BookDTO book = (BookDTO) scenarioContext.getContextObject(bookId);

        Response response = getBookService().getBooks(book);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        List<BookDTO> bookDTOList = mapperService.mutateObjectList(response, BookDTO.class);

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
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        List<BookDTO> bookDTOList = mapperService.mutateObjectList(response, BookDTO.class);
        BookDTO actBook = bookDTOList
                .stream()
                .filter(b -> Objects.equals(b.getTitle(), expBook.getTitle()))
                .findFirst()
                .orElse(null);

        assert actBook != null;
        assertThat(actBook).isEqualTo(expBook);
    }

    @When("delete book {word} for user {word} -> {int}")
    public void deleteBook(final String bookId, final String userId, final int responseCode) {
        UserDTO user = (UserDTO) scenarioContext.getContextObject(userId);
        BookDTO book = (BookDTO) scenarioContext.getContextObject(bookId);

        Response response = getBookService().deleteBook(user, book);
        assertThat(response.getStatusCode()).isEqualTo(responseCode);
    }
}
