package com.example.amicale.Data.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate; // Import de LocalDate
import java.util.List;

@Entity
@Table(name = "publications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED)
public class Publication extends AbstractEntity{

    @Column(nullable = false)
    protected String title;

    @Column(nullable = false)
    protected String author;

    protected LocalDate datePublication ;


    // Méthodes pour obtenir les dates formatées
    public String getDatePublicationFormatted() {
        return datePublication != null ? datePublication.toString() : null; // Utilisation de toString pour le format "yyyy-MM-dd"
    }


}
