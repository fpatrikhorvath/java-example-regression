package com.regression.framework.service;

import com.regression.framework.rest.client.BookClient;
import com.regression.framework.rest.request.CreateBookForUserRequestDTO;
import com.regression.framework.rest.response.BookDTO;
import com.regression.framework.rest.response.UserDTO;
import com.regression.framework.service.util.FakerService;
import io.cucumber.spring.ScenarioScope;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class BookService {
    private static final Logger LOG = LogManager.getLogger(BookService.class);

    private final BookClient   bookClient;
    private final FakerService fakerService;

    public BookService(final BookClient bookClient,
                       final FakerService fakerService) {
        this.bookClient   = bookClient;
        this.fakerService = fakerService;
    }


    public BookDTO initContextBook(final Long userId) {
        final BookDTO book = new BookDTO();
        book.setUserId(userId);
        book.setAuthor(fakerService.book().author());
        book.setTitle(fakerService.book().title());

        LOG.debug("Book: {}", book);
        return book;
    }

    public Response registerBook(final BookDTO book) {
        final CreateBookForUserRequestDTO body = new CreateBookForUserRequestDTO();
        body.setAuthor(book.getAuthor());
        body.setTitle(book.getTitle());

        return bookClient.postBook(book.getUserId(), body);
    }


    public Response getBooks(final BookDTO book) {
        return bookClient.getBooksForUser(book.getUserId());
    }

    public Response deleteBook(final UserDTO user, final BookDTO book) {
        return bookClient.deleteBook(user.getId(), book.getUserId());
    }
}