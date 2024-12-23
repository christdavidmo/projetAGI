package com.example.amicale.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate; // Import de LocalDate
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mandats")
public class Mandat extends AbstractEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "president_id", nullable = false)
    private Member president;  // Le président pendant ce mandat

    @OneToMany(mappedBy = "mandat", cascade = CascadeType.ALL)
    private List<MemberMandatRole> memberMandatRoles; // Liste des rôles des membres dans ce mandat


    private LocalDate dateDebut;
    private LocalDate dateFin;



    // Méthodes pour obtenir les dates formatées
    public String getDateDebutFormatted() {
        return dateDebut != null ? dateDebut.toString() : null; // Utilisation de toString pour le format "yyyy-MM-dd"
    }

    public String getDateFinFormatted() {
        return dateFin != null ? dateFin.toString() : null; // Utilisation de toString pour le format "yyyy-MM-dd"
    }

    public List<Member> getMembers() {
        return memberMandatRoles != null ? memberMandatRoles.stream()
                .map(MemberMandatRole:: getMember)
                .collect(Collectors.toList()) : new ArrayList<Member>();
    }
}
