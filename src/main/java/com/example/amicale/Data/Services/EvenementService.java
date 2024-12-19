package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EvenementService {

    Evenement getEvenementById(Long id);
    Evenement saveEvenement(Evenement evenement);
    Evenement getEvenementByName(String name);
    Boolean existEvenementById(Long id);

    Page<Evenement> getAllEvenement(Pageable pageable);
    Page<Evenement> getFilterEvenement(String name, Pageable pageable);
}
