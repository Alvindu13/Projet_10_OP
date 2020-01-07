package com.library.api.persistance.svc.svcImpl;

import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.Exemplaire;
import com.library.api.persistance.dao.repository.AppUserRepository;
import com.library.api.persistance.dao.repository.BookRepository;
import com.library.api.persistance.dao.repository.ExemplaireRepository;
import com.library.api.persistance.dao.repository.FileAttenteRsvRepository;
import com.library.api.persistance.svc.contracts.BookingSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingSvcImpl implements BookingSvc {

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


    @Autowired
    ExemplaireRepository bookExemplaryRepo;

    /**
     * The App user repository.
     */
    @Autowired
    FileAttenteRsvRepository fileAttenteRsvRepository;

    /**
     * Emprunter 1 exemplaire d'un livre => au moins 1 exemplaire du livre (titre) de disponible
     *
     * @param bookExemplary
     * @param username
     */
    @Override
    public void borrow(Exemplaire bookExemplary, String username) {
        bookExemplary.setBorrower(appUserRepository.findByUsername(username));
        bookExemplary.setBorrowDate(LocalDate.now());
        bookExemplary.setAvailable(false);
        bookExemplaryRepo.save(bookExemplary);
    }

    /**
     * Réserver 1 exemplaire d'un livre => aucun exemplaire du livre n'est disponible
     * @param bookExemplary
     * @param username
     */
    @Override
    public void reserve(Exemplaire bookExemplary, String username) {


    }

    @Override
    public void extend(Exemplaire bookExemplary, String username) {

    }

    @Override
    public void bookingChoose(Exemplaire bookExemplary, String username) {
        if(bookExemplary.getAvailable()){
            borrow(bookExemplary, username);
        } else {
            //addQueueToReserve(book, username);
            //reserve(book, username);
        }
    }


}
