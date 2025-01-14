package com.example.amicale.Data.Entity;


import com.example.amicale.Data.Enumeration.TypeEcole;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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

    private String ecoleName;
    @ManyToMany(mappedBy = "ecoles")
    private Set<Ressources> ressources;
}
