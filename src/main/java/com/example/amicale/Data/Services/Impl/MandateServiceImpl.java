package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Repository.MandatRepository;
import com.example.amicale.Data.Services.MandatService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MandateServiceImpl implements MandatService {


    private final MandatRepository mandatRepository;

    @Override
    public Page<Mandat> GetAllMandat(Pageable pageable) {
        // Créer un Pageable avec un tri par dateDebut, ordre décroissant (le plus récent en premier)
        Pageable sortedByDateDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("dateDebut")));
        return mandatRepository.findAll(sortedByDateDesc);
    }

    @Override
    public List<Member> GetMandatById(Long MandatId) {

        Mandat mandat = mandatRepository.findById(MandatId).get();

        if( mandat != null){
            return null ;
        }
        return null;
    }
}
