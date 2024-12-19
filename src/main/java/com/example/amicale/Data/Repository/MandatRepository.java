package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MandatRepository extends JpaRepository<Mandat,Long> {


    Page<Mandat> findAll(Pageable pageable);
    Mandat findMandatByPresidentId(Long presidentId);


}
