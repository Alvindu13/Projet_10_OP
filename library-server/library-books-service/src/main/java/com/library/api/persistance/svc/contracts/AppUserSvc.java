/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.svc.contracts;

import com.library.api.persistance.dao.model.AppUser;

import java.util.List;

/**
 * The interface App user svc.
 */
public interface AppUserSvc {

    /**
     * Find all users list.
     *
     * @return the list
     */
    List<AppUser> findAllUsers();

    /**
     * Find by username app user.
     *
     * @param username the username
     * @return the app user
     */
    AppUser findByUsername(String username);

    /**
     * Save new user.
     *
     * @param newAppUser
     */
    void save(AppUser newAppUser);
}
