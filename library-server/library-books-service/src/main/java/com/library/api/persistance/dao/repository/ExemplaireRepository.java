package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

    Exemplaire findExemplaireById(Long bookId);

    Exemplaire findExemplaireByAvailableIsTrue();

    List<Exemplaire> findExemplaireByBook(Long bookId);
}
