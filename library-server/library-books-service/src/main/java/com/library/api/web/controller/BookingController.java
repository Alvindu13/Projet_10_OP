package com.library.api.web.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.dao.repository.ExemplaireRepository;
import com.library.api.persistance.svc.contracts.AppUserSvc;
import com.library.api.persistance.svc.contracts.BookSvc;
import com.library.api.persistance.svc.contracts.BookingSvc;
import com.library.api.persistance.svc.contracts.FileAtttenteRsvSvc;
import com.library.api.security.jwt.DecodeToken;
import com.library.api.security.jwt.JwtProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Book controller.
 */
@Api( description="API CRUD's operations to books.")
@RestController
@RequestMapping("/booking")
public class BookingController {


    @Autowired
    private BookSvc bookSvc;

    @Autowired
    private BookingSvc bookingSvc;

    @Autowired
    private FileAtttenteRsvSvc fileAtttenteRsvSvc;

    @Autowired
    private AppUserSvc appUserSvc;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private ExemplaireRepository bookExemplaryRepo;


    /**
     * Emprunter un livre => au moins 1 exemplaire de ce livre est disponible.
     *
     * @param bookId  the book id
     * @param request the front request with jwt token
     */
    @ApiOperation(value = "User borrow selected book")
    @PatchMapping("/{bookId}/borrow")
    void borrowBook(@PathVariable("bookId") Long bookId, HttpServletRequest request) {
        DecodedJWT decodedJWT = DecodeToken.decodeJWT(request, jwtProperties);
        String username = decodedJWT.getSubject();
        bookingSvc.borrow(bookExemplaryRepo.findExemplaireById(bookId), username);
    }

    /**
     * Réserver un livre => si aucun exemplaire de ce livre n'est dispo
     *
     * @param bookId  the book id
     * @param request the front request with jwt token
     */
    @ApiOperation(value = "User reserve selected book")
    @PatchMapping("/{bookId}/reserve")
    void reserveBook(@PathVariable("bookId") Long bookId, HttpServletRequest request) {
        DecodedJWT decodedJWT = DecodeToken.decodeJWT(request, jwtProperties);
        String username = decodedJWT.getSubject();
        bookingSvc.reserve(bookId, username);
    }






















    /**
     * Ajoute l'user qui clique sur réserver dans la file d'attente
     *
     * @param bookId  the book id
     * @param request the front request with jwt token
     */
    /*@ApiOperation(value = "User added queue")
    @PostMapping("/{bookId}/queue")
    void addUserToWaitingQueue(@PathVariable("bookId") Long bookId, HttpServletRequest request) {
        DecodedJWT decodedJWT = DecodeToken.decodeJWT(request, jwtProperties);
        String username = decodedJWT.getSubject();
        fileAtttenteRsvSvc.addUserInWaitingQueue(appUserSvc.findByUsername(username), bookSvc.findBookById(bookId));
        //bookingSvc.reserve(bookExemplaryRepo.findExemplaireById(bookId), username);
    }*/






}
