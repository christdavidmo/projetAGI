package com.example.amicale.Data.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "actualites")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Actualite  extends Publication{
    private String Contenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createur_id", nullable = false)
    private Member createurA; // le createur de l'ectualite
}
