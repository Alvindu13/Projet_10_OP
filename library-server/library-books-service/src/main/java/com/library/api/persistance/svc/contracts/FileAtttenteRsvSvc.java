package com.library.api.persistance.svc.contracts;

import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.Exemplaire;

public interface FileAtttenteRsvSvc {


    void addUserInWaitingQueue(AppUser appUser, Book book);

    void removeUserInWaitingQueue(AppUser appUser, Book book);

    long placeInQueueUser(AppUser appUser);

}
