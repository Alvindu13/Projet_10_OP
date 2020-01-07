package com.library.api.persistance.dao.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="exemplaires")
public class Exemplaire {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @Nullable
    private Book book;

    //@Column(columnDefinition = "boolean default true")
    private Boolean available;
    @Nullable
    private Boolean isProlongation;
    @Nullable
    private LocalDate borrowDate;
    @ManyToOne
    @Nullable
    private AppUser borrower;


}
