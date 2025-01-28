package com.example.amicale.Data.Entity;


import com.example.amicale.Data.Enumeration.TypeEcole;
import com.example.amicale.Data.Enumeration.TypeRessource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ressources")
public class Ressources extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Cette annotation permet à Hibernate de gérer l'auto-incrémentation
    private Long id;


    private TypeRessource type;

    @JoinColumn(nullable = true)
    private String Description;

    // Relation ManyToOne : une ressource appartient à une seule école
    @ManyToOne
    @JoinColumn(name = "ecole_id", nullable = false) // clé étrangère vers l'Ecole
    private Ecole ecole;


    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    protected String title;

    protected LocalDate datePublication ;


    // Méthodes pour obtenir les dates formatées
    public String getDatePublicationFormatted() {
        return datePublication != null ? datePublication.toString() : null; // Utilisation de toString pour le format "yyyy-MM-dd"
    }


}
