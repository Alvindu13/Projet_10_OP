package com.library.api.persistance.svc.contracts;

import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.Exemplaire;

public interface BookingSvc {

    void borrow(Exemplaire bookExemplary, String username);

    void reserve(Long bookId, String username);

    void cancelReserve(Long bookId, String username);

    void extend(Exemplaire bookExemplary, String username);

    void bookingChoose(Exemplaire bookExemplary, String username);
}
