/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo;

import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * The type Scheduled tasks.
 */
@Component
public class ScheduledTasks {

    @Value("${library.api.url}")
    private String apiUrl;

    @Autowired
    private EmailService emailService;

    /**
     * Batch scheduled. Send a email to customer all days à 11 am.
     */
    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 11 ? * MON-SAT")
    public void batchScheduled() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Book>> response = restTemplate.exchange(
                apiUrl + "/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>(){});

        System.out.println("scheduled work : " + response.getBody());

        List<Book> books = response.getBody();

        /*
        Send a email to user if the current date is superior at the starter date to send email
         */
        if (books != null) {
            books.forEach(book -> {
                if (book.getBorrowDate() != null) {
                    LocalDate dateStartEmail = book.getBorrowDate().plus(4, ChronoUnit.WEEKS);
                    LocalDate currentDate = LocalDate.now();
                    if((ChronoUnit.DAYS.between(dateStartEmail, currentDate) >= 0)){
                        this.emailService.sendEmail(book);
                    }
                }
            });
        }
    }


    /**
     * Batch scheduled. Send a email to customer all days à 11 am.
     */
    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 11 ? * MON-SAT")
    public void bookingBatch() {

        /*RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Book>> response = restTemplate.exchange(
                apiUrl + "/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>(){});

        System.out.println("scheduled work : " + response.getBody());

        List<Book> books = response.getBody();


        if (books != null) {
            books.forEach(book -> {
                if (book.getBorrowDate() != null) {
                    LocalDate dateStartEmail = book.getBorrowDate().plus(4, ChronoUnit.WEEKS);
                    LocalDate currentDate = LocalDate.now();
                    if((ChronoUnit.DAYS.between(dateStartEmail, currentDate) >= 0)){
                        this.emailService.sendEmail(book);
                    }
                }
            });
        }*/
    }

}
