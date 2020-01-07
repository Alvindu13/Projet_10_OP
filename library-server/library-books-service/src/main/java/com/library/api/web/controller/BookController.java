/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.web.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.library.api.persistance.svc.contracts.BookSvc;
import com.library.api.persistance.dao.model.Book;
import com.library.api.security.jwt.DecodeToken;
import com.library.api.security.jwt.JwtProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


/**
 * The type Book controller.
 */
@Api( description="API CRUD's operations to books.")
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private BookSvc bookSvc;

    /**
     * Find all books by keyword.
     *
     * @param keyword the keyword
     * @return the books list
     */
    @ApiOperation(value = "Find all books by keyword")
    @GetMapping("/selected/{keyword}")
    List<Book> findAllByKeyword(@PathVariable("keyword") String keyword) {
        return bookSvc.findAllByNameDistinctContains(keyword);
    }

    /**
     * Gets all books.
     *
     * @return the books list
     */
    @ApiOperation(value = "Gets all books")
    @GetMapping
    List<Book> getAllBooks() { return bookSvc.findAllBooks(); }


    /**
     * Save one book.
     *
     */
    @ApiOperation(value = "Save one book")
    @PostMapping
    void saveBook(@RequestBody Book newBook, HttpServletRequest request) {
        DecodedJWT decodedJWT = DecodeToken.decodeJWT(request, jwtProperties);
        String username =decodedJWT.getSubject();
        bookSvc.save(newBook, username); }

    /**
     * Count books by name.
     *
     * @param name the name
     * @return the long quantity
     */
    @ApiOperation(value = "Count books by title")
    @GetMapping("/count/{name}")
    Long count(@PathVariable("name") String name) { return bookSvc.countByName(name); }

    /**
     * Find all books by borrower.
     *
     * @param request the front request with jwt token
     * @return the books' borrower list
     */
    /*@ApiOperation(value = "Find all books by borrower")
    @GetMapping("/user")
    List<Book> findAllByBorrower(HttpServletRequest request) {
        DecodedJWT decodedJWT = DecodeToken.decodeJWT(request, jwtProperties);
        String username = decodedJWT.getSubject();
       return bookSvc.findAllByBorrowerUsername(username);
    }*/


    /**
     * Stop reservation.
     *
     * @param bookId
     */
    /*@ApiOperation(value = "User return book")
    @PatchMapping("/{bookId}/reserve/stop")
    void stopReserveBookReturn(@PathVariable("bookId") Long bookId) {
        bookSvc.stopReserve(bookSvc.findBookById(bookId));
    }*/

    /**
     * Reserve extend.
     *
     * @param bookId the book id
     */
    /*@ApiOperation(value = "User extend book's reservation one time to 4 weeks")
    @PatchMapping("/extend/{bookId}")
    void reserveExtend( @PathVariable("bookId") Long bookId) {
        bookSvc.extend(bookSvc.findBookById(bookId));
    }*/

}

