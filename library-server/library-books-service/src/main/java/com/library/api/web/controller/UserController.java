/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.web.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.svc.contracts.AppUserSvc;
import com.library.api.security.AccountService;
import com.library.api.security.jwt.DecodeToken;
import com.library.api.security.jwt.JwtProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The type User controller.
 */
@Api( description="API CRUD's operations to users.")
@RestController
@RequestMapping("/appUsers")
public class UserController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private AppUserSvc appUserSvc;

    /**
     * Find all users list.
     *
     * @return list
     */
    @ApiOperation(value = "Gets all users")
    @GetMapping()
    List<AppUser> findAllUsers() {
        return appUserSvc.findAllUsers();
    }

    /**
     * Find user by username app user.
     *
     * @param request the request
     * @return app user
     */
    @ApiOperation(value = "Get one user by username")
    @GetMapping("/selected/")
    AppUser findUserByUsername(HttpServletRequest request) {
        DecodedJWT decodedJWT = DecodeToken.decodeJWT(request, jwtProperties);
        String username = decodedJWT.getSubject();
        return appUserSvc.findByUsername(username);
    }


}

