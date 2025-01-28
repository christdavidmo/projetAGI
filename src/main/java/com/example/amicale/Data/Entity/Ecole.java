package com.example.amicale.Data.Entity;


import com.example.amicale.Data.Enumeration.TypeEcole;
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
@Table(name = "ecoles")
public class Ecole extends AbstractEntity{

    @Column(nullable = false)
    private String ecoleName;

    // Relation OneToMany avec Ressources : une école peut avoir plusieurs ressources
    @OneToMany(mappedBy = "ecole", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ressources> ressources;
}
