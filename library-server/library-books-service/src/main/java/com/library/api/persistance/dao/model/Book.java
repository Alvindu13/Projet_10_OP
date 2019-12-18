/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.library.api.persistance.dao.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * The type Book.
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private Double price;
    private String genre;

    @Column(columnDefinition = "boolean default true")
    private Boolean available;

    @Nullable
    private Boolean isProlongation;

    @Nullable
    private String photoName;

    @Nullable
    private LocalDate borrowDate;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    @Nullable
    private AppUser borrower;

}


