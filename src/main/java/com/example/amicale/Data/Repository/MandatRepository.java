package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MandatRepository extends JpaRepository<Mandat,Long> {


    Page<Mandat> findAll(Pageable pageable);
    Page<Mandat> findByDateDebutContaining(LocalDate dateDebut, Pageable pageable);


    Mandat findMandatByPresidentId(Long presidentId);
    Mandat findMandatByDateDebut(LocalDate dateDebut);




    // Méthode pour récupérer les membres ayant un rôle dans un mandat spécifique
    List<MemberMandatRole> findMandatById(Long mandatId);


}
