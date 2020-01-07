/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.svc.contracts;

import com.library.api.persistance.dao.model.Book;

import java.util.List;

/**
 * The interface Book svc.
 */
public interface BookSvc {



    /**
     * Find all by name contains list.
     *
     * @param keyword the keyword
     * @return the list
     */
    List<Book> findAllByNameDistinctContains(String keyword);

    List<Book> findAllByTitle(String title);

    /**
     * Find all books list.
     *
     * @return the list
     */
    List<Book> findAllBooks();

    /**
     * Count book by name long.
     *
     * @param name the name
     * @return the long
     */
    Long countByName(String name);

    /**
     * Find all by borrower username.
     *
     * @param username the username
     * @return the list
     */
    //List<Book> findAllByBorrowerUsername(String username);

    /**
     * Find book by id book.
     *
     * @param bookId the book id
     * @return the book
     */
    Book findBookById(Long bookId);



    /**
     * Save one book.
     *
     * @param newBook
     * @param username
     */
    void save(Book newBook, String username);

    /**
     * Stop book reserve.
     *
     * @param bookById
     */
    void stopReserve(Book bookById);
}
