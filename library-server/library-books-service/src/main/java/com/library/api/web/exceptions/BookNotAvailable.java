package com.library.api.web.exceptions;

public class BookNotAvailable extends RuntimeException {

    public BookNotAvailable(Long id) {
        super("Book id is not available : " + id);
    }

}
