/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The interface App user repository.
 */
@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Find by username app user.
     *
     * @param username the username
     * @return the app user
     */
    AppUser findByUsername(@Param("username") String username);
}
