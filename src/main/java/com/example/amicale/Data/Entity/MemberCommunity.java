package com.example.amicale.Data.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "members_community")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCommunity extends Users{

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


}
