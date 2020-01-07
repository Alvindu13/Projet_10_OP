package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.FileAttenteReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface FileAttenteRsvRepository extends JpaRepository<FileAttenteReservation, Long> {

    List<FileAttenteReservation> findAllByBookOrderByPlaceInQueue(Book book);

    FileAttenteReservation findFileAttenteReservationByBookAndUser(Book book, AppUser user);


    List<FileAttenteReservation> findFileAttenteReservationByBook(Book bookById);

    List<FileAttenteReservation> findFileAttenteReservationByBookIdAndUserId(Long bookId, Long id);
}
