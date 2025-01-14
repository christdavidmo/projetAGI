package com.example.amicale.Data.Entity;

import com.example.amicale.Data.Enumeration.Statut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate; // Import de LocalDate
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "members")
public class Member extends Users{

    @Column(nullable = false)
    private String sexe ;
    private LocalDate dateOfJoining;

    @Column(  nullable = false)
    private String nom ;

    @Column(name = "prenoms",nullable = false)
    private String prenoms;


    private String email ;

    @Column(nullable = false)
    private String matricule;

    // Champ pour stocker l'URL ou le chemin de la photo
    @Column(nullable = true)
    private String photoUrl ;


    // Relation OneToMany avec MemberMandatRole
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberMandatRole> memberMandatRoles;  // Liste des rôles associés à ce membre dans les mandats

    @OneToMany(mappedBy = "createur" , fetch = FetchType.LAZY)
    private List<Evenement> evenements;

    @OneToMany(mappedBy = "createurA", fetch = FetchType.LAZY)
    private List<Actualite> actualites ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Statut statut = Statut.ACTIF ; // valeur par d"faut ;


}
