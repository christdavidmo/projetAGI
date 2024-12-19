package com.example.amicale.Data.Entity;

import jakarta.persistence.*;
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

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "createur_id", nullable = false)
    private Member createur; // le createur de l'evenement dans le bureau
}
