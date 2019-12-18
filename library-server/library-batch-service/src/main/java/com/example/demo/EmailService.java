/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo;

import com.example.demo.model.Book;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Email service.
 */
@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JavaMailSender javaMailSender;


    /**
     * Instantiates a new Email service.
     *
     * @param javaMailSender the java mail sender
     */
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Send email.
     *
     * @param book the book
     */
    public void sendEmail(Book book) {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        var mailMessage = new SimpleMailMessage();

        mailMessage.setTo(book.getBorrower().getUsername());
        mailMessage.setSubject("Bibliothèque réservation - retour" );
        mailMessage.setText("Bonjour, vous avez réservé le livre " + book.getName() + ". Pensez à le ramener avant la date d'échéance : " + book.getBorrowDate().toString());

        javaMailSender.send(mailMessage);
        logger.info("mail avec succès à  : " + book.getBorrower().getUsername());
    }
}
