package com.regression.framework.stores;

import com.regression.framework.service.BookService;
import com.regression.framework.service.UserService;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Service;

@Service
@ScenarioScope
public class UserLayerContextStore {
    private final UserService userService;
    private final BookService bookService;

    public UserLayerContextStore(final UserService userService,
                                 final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
