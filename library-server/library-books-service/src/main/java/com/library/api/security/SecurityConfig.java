/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.security;

import com.library.api.security.jwt.JWTAuthenticationFilter;
import com.library.api.security.jwt.JWTAuthorizationFilter;
import com.library.api.security.jwt.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //Normal warning => qualifier is optional)
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Spring Security offre une API fluent pour configurer une authentification à partir de l'objet AuthenticationManagerBuilder.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    /**
     * Cette méthode oveerride de spring permet de configurer les accès aux requêtes et de créer une session STATELESS (sans session)
     * On disable le crsf pour le token
     */
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();//obligatoire pour jwt token car empêche les requêtes en post etc
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //on utilise plus les sessions de spring (sessions de l'user en mémoire)
        http.authorizeRequests().antMatchers("/login/**", "/register/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/books/**").authenticated();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/books/user").authenticated();
        // permet de faire fonctionner H2-console (html page)
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests().anyRequest().permitAll(); // a delete après test
        http.addFilter(new JWTAuthenticationFilter(jwtProperties, authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(jwtProperties) , UsernamePasswordAuthenticationFilter.class);
    }

}

