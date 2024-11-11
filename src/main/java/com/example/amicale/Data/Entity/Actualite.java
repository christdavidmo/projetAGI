package com.example.amicale.Data.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne
    private OfficeMember OfficeMember;
}
