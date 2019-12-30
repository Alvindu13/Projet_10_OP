package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
}
