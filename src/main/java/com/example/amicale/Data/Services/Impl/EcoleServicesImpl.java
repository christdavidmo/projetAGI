package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Ecole;
import com.example.amicale.Data.Repository.EcoleRepository;
import com.example.amicale.Data.Services.EcoleServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EcoleServicesImpl implements EcoleServices {

    private final EcoleRepository ecoleRepository;

    @Override
    public List<Ecole> getEcoles() {
        return ecoleRepository.findAll();
    }

    @Override
    public Ecole getEcole(Long id) {
        return ecoleRepository.findEcoleById(id);
    }
}
