/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.security;

import com.library.api.persistance.dao.model.AppRole;
import com.library.api.persistance.dao.model.AppUser;

/**
 * The interface Account service.
 */
public interface AccountService {

    /**
     * Save user app user.
     *
     * @param username          the username
     * @param password          the password
     * @param confirmedPassword the confirmed password
     * @return the app user
     */
    AppUser saveUser(String username, String password, String confirmedPassword);

    /**
     * Save role app role.
     *
     * @param appRole the app role
     * @return the app role
     */
    AppRole saveRole(AppRole appRole);

    /**
     * Load user by username app user.
     *
     * @param username the username
     * @return the app user
     */
    AppUser loadUserByUsername(String username);

    /**
     * Add role to user.
     *
     * @param username the username
     * @param roleName the role name
     */
    void addRoleToUser(String username, String roleName);

}
