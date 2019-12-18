/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.svc.svcImpl;

import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.dao.repository.AppUserRepository;
import com.library.api.persistance.svc.contracts.AppUserSvc;
import com.library.api.security.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserSvcImpl implements AppUserSvc {



    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public List<AppUser> findAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void save(AppUser newAppUser) {
        appUserRepository.save(newAppUser);

    }
}
