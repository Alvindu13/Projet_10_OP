/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api;

import com.library.api.persistance.dao.model.AppRole;
import com.library.api.persistance.dao.model.AppUser;
import com.library.api.persistance.dao.repository.BookRepository;
import com.library.api.security.AccountService;
import com.library.api.persistance.dao.model.Book;
import com.library.api.security.jwt.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties(JwtProperties.class)
//@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class StartBookApplication {


    @Autowired
    private RepositoryRestConfiguration restConfiguration;


    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartBookApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService, BookRepository bookSvc){

        restConfiguration.exposeIdsFor(AppUser.class);
        restConfiguration.exposeIdsFor(AppRole.class);
        restConfiguration.exposeIdsFor(Book.class);

        return args -> {
            accountService.saveRole((new AppRole(null, "USER")));
            accountService.saveRole((new AppRole(null, "ADMIN")));

            Stream.of("alcaraz.jeremie@hotmail.fr", "alvin.mysterio@gmail.com", "user3@gmail.com", "admin@gmail.com").forEach(un->{
                accountService.saveUser(un, "1234", "1234");
            });
            accountService.addRoleToUser("admin@gmail.com", "ADMIN");


            bookSvc.save(new Book(
                    1L, "A Guide to the Bodhisattva Way of Life", "Santideva",
                    "Aventure", null));
            bookSvc.save(new Book(
                    2L, "Tesla", "Geneva",
                    "Aventure", null));

        };


    }

    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
}
