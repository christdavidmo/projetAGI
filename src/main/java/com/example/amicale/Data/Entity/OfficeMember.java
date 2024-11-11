package com.example.amicale.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "office_members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfficeMember extends Users{

    @Column(nullable = false)
    private String Sexe ;

    private Date dateOfJoining;

    @Column(nullable = false)
    private String Nom ;

    @Column(nullable = false)
    private String Prenoms;

    private String Email ;

    @Column(nullable = false)
    private String Matricule;

    @OneToMany(mappedBy = "OfficeMember")
    List<Evenement> evenements;

    @OneToMany(mappedBy = "OfficeMember")
    List<Actualite> actualites;

    // Autres attributs si nécessaire
}
