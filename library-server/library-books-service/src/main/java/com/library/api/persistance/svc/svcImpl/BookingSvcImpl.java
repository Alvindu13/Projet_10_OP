package com.library.api.persistance.svc.svcImpl;

import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.Exemplaire;
import com.library.api.persistance.dao.model.FileAttenteReservation;
import com.library.api.persistance.dao.repository.AppUserRepository;
import com.library.api.persistance.dao.repository.BookRepository;
import com.library.api.persistance.dao.repository.ExemplaireRepository;
import com.library.api.persistance.dao.repository.FileAttenteRsvRepository;
import com.library.api.persistance.svc.contracts.AppUserSvc;
import com.library.api.persistance.svc.contracts.BookSvc;
import com.library.api.persistance.svc.contracts.BookingSvc;
import com.library.api.web.exceptions.BookAlreadyReserveByThisUser;
import com.library.api.web.exceptions.SizeQueueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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

    @Autowired
    FileAttenteRsvSvcImpl fileAtttenteRsvSvc;

    @Autowired
    AppUserSvc appUserSvc;

    @Autowired
    BookSvc bookSvc;


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

        // SI l'utilisateur a une réservation, alors fermée la réservation
        if(fileAttenteRsvRepository.findFileAttenteReservationByBookAndUser(bookExemplary.getBook(), appUserRepository.findByUsername(username)) != null){
            cancelReserve(Objects.requireNonNull(bookExemplary.getBook()).getId(), username);
        }

        bookExemplary.setBorrower(appUserRepository.findByUsername(username));
        bookExemplary.setBorrowDate(LocalDate.now());
        bookExemplary.setAvailable(false);
        bookExemplaryRepo.save(bookExemplary);
    }

    /**
     * Réserver 1 exemplaire d'un livre => aucun exemplaire du livre n'est disponible
     * @param username
     */
    @Override
    public void reserve(Long bookId, String username) {

        int sizeQueue = fileAttenteRsvRepository.findFileAttenteReservationByBook(bookRepository.findBookById(bookId)).size();
        int maxSizeQueue = bookExemplaryRepo.findAllByBookId(bookId).size()*2;

        if ( !fileAttenteRsvRepository.findFileAttenteReservationByBookIdAndUserId(bookId, appUserSvc.findByUsername(username).getId()).isEmpty() ) throw new BookAlreadyReserveByThisUser(username, bookId);

        if ( sizeQueue < maxSizeQueue )
            fileAtttenteRsvSvc.addUserInWaitingQueue(appUserSvc.findByUsername(username), bookSvc.findBookById(bookId));
        else throw new SizeQueueException(sizeQueue);

    }

    /**
     * Annuler la réservation
     * @param username
     */
    @Override
    public void cancelReserve(Long bookId, String username) {
        FileAttenteReservation fileAttenteReservation = fileAttenteRsvRepository.findFileAttenteReservationByBookAndUser(bookRepository.findBookById(bookId), appUserRepository.findByUsername(username));
        fileAttenteRsvRepository.delete(fileAttenteReservation);
    }

    /**
     * Enable to extend one reservation of 4 weeks.
     *
     * @param bookExemplary book's exemplary selected to extend the reservation.
     */
    @Override
    public void extend(Exemplaire bookExemplary, String username) {
        LocalDate ldt = bookExemplary.getBorrowDate();

        // add 4 weeks of delay to reservation book
        Objects.requireNonNull(bookExemplary.getIsProlongation(), "prolongation parameter is null");
        if (!bookExemplary.getIsProlongation()) {
            bookExemplary.setBorrowDate(ldt != null ? ldt.plus(4, ChronoUnit.WEEKS) : null);
            bookExemplary.setIsProlongation(true);
            bookExemplaryRepo.save(bookExemplary);
        } else {
            //logger.info("reservation has already extend ! ");

        }

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
