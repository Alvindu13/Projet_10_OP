package com.library.api.persistance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="waitingList")
public class FileAttenteReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JoinColumn(name = "address__id", referencedColumnName = "id")
    @ManyToOne
    private Book bookId;

    @ManyToOne
    private AppUser userId;


    private Long placeInQueue;

    public FileAttenteReservation(Book bookId, AppUser userId, Long placeInQueue) {
        this.bookId = bookId;
        this.userId = userId;
        this.placeInQueue = placeInQueue;
    }
}
