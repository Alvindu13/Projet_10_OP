package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.dao.model.FileAttenteReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface FileAttenteRsvRepository extends JpaRepository<FileAttenteReservation, Long> {
    //FileAttenteReservation findByUsers(AppUser appUser);
}
