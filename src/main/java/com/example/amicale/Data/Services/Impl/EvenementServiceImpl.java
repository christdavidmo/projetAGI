package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Repository.EvenementRepository;
import com.example.amicale.Data.Services.EvenementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;


    @Override
    public Evenement getEvenementById(Long id) {
        Evenement evenement = evenementRepository.findEvenementById(id);

        if (evenement == null){
           throw new SecurityException("ce evenement n'existe pas");
        }
        return evenementRepository.findEvenementById(id);
    }

    @Override
    public Evenement saveEvenement(Evenement evenement) {

        return evenementRepository.save(evenement);
    }


    @Override
    public Evenement getEvenementByName(String name) {

       Evenement Name = evenementRepository.findEvenementByTitle(name);
       if (Name == null){
           throw new SecurityException("ce evenement n'existe pas");
       }

        return evenementRepository.findEvenementByTitle(name);
    }

    @Override
    public Page<Evenement> getAllEvenement(Pageable pageable) {
        return evenementRepository.findAll(pageable);
    }

    @Override
    public Page<Evenement> getFilterEvenement(String name, Pageable pageable) {
        if(name != null && !name.isEmpty()){
            return evenementRepository.findEvenementByTitleContaining(name, pageable);
        }
        return evenementRepository.findAll(pageable);
    }


    @Override
    public Boolean existEvenementById(Long id) {
        return evenementRepository.existsById(id);
    }
}
