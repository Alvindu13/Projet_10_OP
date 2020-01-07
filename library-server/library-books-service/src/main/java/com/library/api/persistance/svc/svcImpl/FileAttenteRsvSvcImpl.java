package com.library.api.persistance.svc.svcImpl;

import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.Exemplaire;
import com.library.api.persistance.dao.model.FileAttenteReservation;
import com.library.api.persistance.dao.repository.FileAttenteRsvRepository;
import com.library.api.persistance.svc.contracts.FileAtttenteRsvSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Une file pour un livre
 */
@Service
public class FileAttenteRsvSvcImpl implements FileAtttenteRsvSvc {

    private Long counter = 0L;
    private Long currentUserId = 0L;

    @Autowired
    FileAttenteRsvRepository fileAttenteRsvRepository;

    /**
     * Ajoute un utilisateur à la file
     * @param appUser
     * @param book
     */
    @Override
    public void addUserInWaitingQueue(AppUser appUser, Book book) {
        counter++;
        FileAttenteReservation fileAttenteReservation = new FileAttenteReservation();
        fileAttenteReservation.setBook(book);
        fileAttenteReservation.setUser(appUser);
        fileAttenteReservation.setPlaceInQueue(counter);
    }

    /**
     * Supprime un utilisateur de la file d'attente du livre en paramètre
     * 2 CAS :
     * -> 48h
     * -> livre recup
     * @param appUser
     * @param book
     */
    @Override
    public void removeUserInWaitingQueue(AppUser appUser, Book book) {
        FileAttenteReservation fileAttenteReservationToRemove = fileAttenteRsvRepository.findFileAttenteReservationByBookAndUser(book, appUser);
        fileAttenteRsvRepository.delete(fileAttenteReservationToRemove);
        //currentUserId = fileAttenteRsvRepository.findAll().get(0).getUser().getId(); //TEMPORAIRE  => pas du tout opti => on charge toute la table à chaque fois
    }

    @Override
    public long placeInQueueUser(AppUser appUser) {
        return 0;
    }
}
