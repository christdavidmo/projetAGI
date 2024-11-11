package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Actualite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActualiteRepository extends JpaRepository<Actualite, Long> {

    Actualite findActualiteById(Long id);
    Actualite findActualiteByTitle(String name);

    Boolean existsActualiteById(Long id);
}
