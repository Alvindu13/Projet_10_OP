/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.svc.svcImpl;

import com.library.api.persistance.svc.contracts.BookSvc;
import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.repository.AppUserRepository;
import com.library.api.persistance.dao.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * The type Book svc.
 */
@Service
public class BookSvcImpl implements BookSvc {

    private static final Logger logger = LoggerFactory.getLogger(BookSvcImpl.class);
    Deque<String> reserveQueue;
    Map<String, Deque> allWaitingQueues = new HashMap<>();



    /**
     * The Repo.
     */
    @Autowired
    BookRepository bookRepository;

    /**
     * The App user repository.
     */
    @Autowired
    AppUserRepository appUserRepository;


    /**
     * Search books by keyword.
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Book> findAllByNameDistinctContains(String keyword) {
        return bookRepository.findDistinctByTitleContains(keyword);
    }


    /**
     * Search books by title.
     *
     * @param title
     * @return
     */
    @Override
    public List<Book> findAllByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }


    /**
     * Find all books of db.
     *
     * @return
     */
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Count books in db.
     *
     * @param title
     * @return
     */
    @Override
    public Long countByName(String title) {
        return bookRepository.countByTitle(title);
    }

    /**
     * Find books by parameters.
     *
     * @param username of user connected
     * @return
     */
    /*@Override
    public List<Book> findAllByBorrowerUsername(String username) {
        return bookRepository.findAllByBorrowerUsername(username);
    }*/

    /**
     * Find book by book_id.
     *
     * @param bookId
     * @return
     */
    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

    @Override
    public void save(Book newBook, String username) {

    }

    @Override
    public void stopReserve(Book bookById) {

    }

    /**
     * Save book.
     *
     * @param newBook the new book
     * @return
     */
    /*@Override
    public void save(Book newBook, String username) {
        newBook.setIsProlongation(false);
        newBook.setAvailable(true);
        newBook.setBorrower(appUserRepository.findByUsername(username));
        bookRepository.save(newBook);
    }*/

    /**
     * Stop book reserve.
     *
     * @param book
     */
    /*@Override
    public void stopReserve(Book book) {
        book.setAvailable(true);
        book.setBorrower(null);
        book.setIsProlongation(false);
        //book.setReturnDate(currentDate);
    }*/

}


