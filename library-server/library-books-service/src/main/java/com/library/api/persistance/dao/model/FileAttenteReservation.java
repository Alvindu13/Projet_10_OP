package com.library.api.persistance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    //@OneToOne
    @JoinColumn(name = "BOOK_ID"/*, unique = true*/)
    @OneToOne(cascade = CascadeType.ALL)
    private Book book;

    //@OneToMany
    @JoinColumn(name = "USER_ID", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private AppUser user;

    private Long placeInQueue;


}
