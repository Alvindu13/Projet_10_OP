/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * The interface App role repository.
 */
@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    /**
     * Find by role name app role.
     *
     * @param roleName the role name
     * @return the app role
     */
    AppRole findByRoleName(String roleName);

}


