package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Evenement;

public interface EvenementService {

    Evenement getEvenementById(Long id);
    Evenement saveEvenement(Evenement evenement);
    Evenement getEvenementByName(String name);
    Boolean existEvenementById(Long id);
}
