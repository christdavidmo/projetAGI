package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Actualite;
import com.example.amicale.Data.Repository.ActualiteRepository;
import com.example.amicale.Data.Services.ActualiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActualiteServiceImpl implements ActualiteService {

    private final ActualiteRepository actualiteRepository;

    @Override
    public Actualite getActualiteByName(String name) {
        return actualiteRepository.findActualiteByTitle(name);
    }

    @Override
    public Actualite getActualiteById(Long id) {

        return actualiteRepository.findActualiteById(id);
    }

    @Override
    public Boolean existActualiteById(Long id) {
        return actualiteRepository.existsActualiteById(id);
    }
}
