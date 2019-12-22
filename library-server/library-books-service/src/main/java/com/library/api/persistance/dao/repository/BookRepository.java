/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * The interface Book repository.
 */
@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find all by borrower username list.
     *
     * @param username the username
     * @return the list
     */
    List<Book> findAllByBorrowerUsername(String username);

    /**
     * Find all by name contains list.
     *
     * @param keyword the keyword
     * @return the list
     */
    //@Query("SELECT DISTINCT(b.name) FROM Book b WHERE b.name = ?1")
    List<Book> findDistinctByTitleContains(String keyword);


    /**
     * Find book by id book.
     *
     * @param bookId the book id
     * @return the book
     */
    Book findBookById(Long bookId);

    /**
     * Count by name long.
     *
     * @param title the title
     * @return the long
     */
    long countByTitle(String title);


    List<Book> findAllByTitle(String title);
}


