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
import java.util.List;
import java.util.Objects;

/**
 * The type Book svc.
 */
@Service
public class BookSvcImpl implements BookSvc {

    private static final Logger logger = LoggerFactory.getLogger(BookSvcImpl.class);


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
     * This method enable to reserve a book
     *
     * @param book     selected book
     * @param username of user connected
     */
    public void reserve(Book book, String username) {

        book.setAvailable(false);
        book.setBorrower(appUserRepository.findByUsername(username));
        book.setBorrowDate(LocalDate.now());
        bookRepository.save(book);
    }


    /**
     * Enable to extend one reservation of 4 weeks
     *
     * @param book book selected to extend it reservation
     */
    public void extend(Book book) {

        LocalDate ldt = book.getBorrowDate();

        // add 4 weeks of delay to reservation book
        Objects.requireNonNull(book.getIsProlongation(), "prolongation parameter is null");
        if (!book.getIsProlongation()) {
            book.setBorrowDate(ldt != null ? ldt.plus(4, ChronoUnit.WEEKS) : null);
            book.setIsProlongation(true);
            bookRepository.save(book);
        } else {
            logger.info("reservation has already extend ! ");

        }
    }

    /**
     * Search books by keyword.
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Book> findAllByNameContains(String keyword) {
        return bookRepository.findDistinctByNameContains(keyword);
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
     * @param name
     * @return
     */
    @Override
    public Long countByName(String name) {
        return bookRepository.countByName(name);
    }

    /**
     * Find books by parameters.
     *
     * @param username of user connected
     * @return
     */
    @Override
    public List<Book> findAllByBorrowerUsername(String username) {
        return bookRepository.findAllByBorrowerUsername(username);
    }

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

    /**
     * Save book.
     *
     * @param newBook the new book
     * @return
     */
    @Override
    public void save(Book newBook, String username) {
        newBook.setIsProlongation(false);
        newBook.setAvailable(true);
        newBook.setBorrower(appUserRepository.findByUsername(username));
        bookRepository.save(newBook);
    }

    /**
     * Stop book reserve.
     *
     * @param book
     */
    @Override
    public void stopReserve(Book book) {
        book.setAvailable(true);
        book.setBorrower(null);
        book.setIsProlongation(false);
        //book.setReturnDate(currentDate);
    }

}


