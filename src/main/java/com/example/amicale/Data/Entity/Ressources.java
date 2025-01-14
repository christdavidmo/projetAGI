package com.example.amicale.Data.Entity;


import com.example.amicale.Data.Enumeration.TypeEcole;
import com.example.amicale.Data.Enumeration.TypeRessource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ressources")
public class Ressources extends Publication{

    private TypeRessource type;
    private String Description;

    @ManyToMany
    @JoinTable(
            name = "ressources_ecoles",
            joinColumns = @JoinColumn(name = "ressource_id"),
            inverseJoinColumns = @JoinColumn(name = "ecole_id")
    )

    private Set<Ecole> ecoles;

    @Column(nullable = false)
    private String path;
}
