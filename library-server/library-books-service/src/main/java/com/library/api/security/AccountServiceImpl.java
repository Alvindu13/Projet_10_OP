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
import com.library.api.persistance.dao.repository.AppRoleRepository;
import com.library.api.persistance.dao.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Account service.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    //idem @Autowired
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Instantiates a new Account service.
     *
     * @param appUserRepository     the app user repository
     * @param appRoleRepository     the app role repository
     * @param bCryptPasswordEncoder the b crypt password encoder
     */
    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Save one user: AppUser.
     *
     * @param username          the username
     * @param password          the password
     * @param confirmedPassword the confirmed password
     * @return
     */
    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword) {

        AppUser user = appUserRepository.findByUsername(username);

        if(user != null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException(("Please confirm your password"));

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setActived(true);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));

        appUserRepository.save(appUser);
        addRoleToUser(username, "USER");

        return appUser;
    }

    /**
     * Save roles: AppRole.
     *
     * @param appRole the app role
     * @return
     */
    @Override
    public AppRole saveRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    /**
     * Load one user: AppUser by username.
     *
     * @param username the username
     * @return
     */
    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    /**
     * Add role(s) to user: AppUser.
     *
     * @param username the username
     * @param roleName the role name
     */
    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getRoles().add(appRole);
    }
}
