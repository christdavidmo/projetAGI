package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Ecole;
import com.example.amicale.Data.Entity.Ressources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RessourcesRepository extends JpaRepository <Ressources,Long>{


    Page<Ressources> findByEcoleAndTitleContainingIgnoreCase(Pageable pageable,Ecole ecole,String ressourceName);
    Page<Ressources> findByEcole(Pageable pageable,Ecole ecole);
    Page<Ressources> findByTitleContaining(String title,Pageable pageable);

    Page<Ressources> findAll(Pageable pageable);
}
