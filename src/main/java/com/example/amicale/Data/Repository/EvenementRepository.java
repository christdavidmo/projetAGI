package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {


    Boolean existsById(long id);
    Evenement findEvenementById(long id);
    Evenement findEvenementByTitle(String name);
}
