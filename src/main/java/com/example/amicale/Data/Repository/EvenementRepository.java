package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {


    Boolean existsById(long id);
    Evenement findEvenementById(long id);
    Evenement findEvenementByTitle(String name);

    Page<Evenement> findAll(Pageable pageable);
    Page<Evenement> findEvenementByTitleContaining(String name,Pageable pageable);
}
