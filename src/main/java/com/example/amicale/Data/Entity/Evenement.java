package com.example.amicale.Data.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "evenements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Evenement extends Publication {


    private String Lieu;

    private String Description;

    @ManyToOne
    private OfficeMember OfficeMember;
}
