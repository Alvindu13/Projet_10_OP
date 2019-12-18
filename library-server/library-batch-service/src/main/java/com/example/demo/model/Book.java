/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type Book.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private Long id;
    private String name;
    private String author;
    private Double price;
    private String genre;
    private Boolean available;
    private Boolean isProlongation;
    private String photoName;
    private LocalDate borrowDate;
    private AppUser borrower;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", available=" + available +
                ", isProlongation=" + isProlongation +
                ", photoName='" + photoName + '\'' +
                ", borrowDate=" + borrowDate +
                ", borrower=" + borrower +
                '}';
    }
}


