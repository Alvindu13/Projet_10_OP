package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.FileAttenteReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAttenteRsvRepository extends JpaRepository<FileAttenteReservation, Long> {
}
