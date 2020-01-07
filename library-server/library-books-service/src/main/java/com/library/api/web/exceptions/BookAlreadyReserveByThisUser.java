package com.library.api.web.exceptions;

public class BookAlreadyReserveByThisUser extends RuntimeException {
    public BookAlreadyReserveByThisUser(String username, Long bookId) {
        super("L'user : " + username + " had ever been borrow the book with id : " + bookId);
    }
}
