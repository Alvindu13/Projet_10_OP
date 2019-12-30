package com.library.api.persistance.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="waiting-list")
public class FileAttenteReservation {

    //@JoinColumn(name = "address__id", referencedColumnName = "id")
    Book bookId;


    AppUser userId;


    Integer placeInQueue;
}
