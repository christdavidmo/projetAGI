package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Repository.EvenementRepository;
import com.example.amicale.Data.Services.EvenementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;


    @Override
    public Evenement getEvenementById(Long id) {
        return evenementRepository.findEvenementById(id);
    }

    @Override
    public Evenement saveEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public Evenement getEvenementByName(String name) {

        return evenementRepository.findEvenementByTitle(name);
    }

    @Override
    public Boolean existEvenementById(Long id) {

        return evenementRepository.existsById(id);
    }
}
