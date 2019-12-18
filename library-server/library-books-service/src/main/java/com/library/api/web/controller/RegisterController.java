/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.web.controller;

import com.library.api.persistance.dao.model.AppUser;
import com.library.api.security.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api( description="API register.")
public class RegisterController {

    @Autowired
    private AccountService accountService;

    /**
     * Register app user.
     *
     * @param userForm the user form
     * @return app user
     */
    @ApiOperation(value = "Save a new user")
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm){
        return accountService.saveUser(
                userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
    }
}

/**
 * The type User form.
 */
@Data @NoArgsConstructor @AllArgsConstructor
class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
}

