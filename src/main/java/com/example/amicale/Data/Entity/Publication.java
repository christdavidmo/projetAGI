package com.example.amicale.Data.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "publications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED)
public class Publication extends AbstractEntity{

    @Column(nullable = false)
    protected String title;

    @Column(nullable = false)
    protected String author;

    protected Date datePublication ;
}
