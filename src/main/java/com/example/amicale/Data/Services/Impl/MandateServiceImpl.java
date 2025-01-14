package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import com.example.amicale.Data.Repository.MandatRepository;
import com.example.amicale.Data.Services.MandatService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Mandat saveMandat(Mandat mandat) {
        return mandatRepository.save(mandat);
    }

    @Override
    public Mandat getMandatById(Long id) {
        return mandatRepository.findById(id).orElse(null);
    }

    @Override
    public List<Member> GetMandatById(Long MandatId) {

        // Vérifie si le mandat existe de manière sécurisée
        Optional<Mandat> mandatOptional = mandatRepository.findById(MandatId);
        if (!mandatOptional.isPresent()) {
            throw new IllegalArgumentException("Mandat avec cet ID non trouvé");
        }
        Mandat mandat = mandatOptional.get();

        // Si la liste des rôles de membre est vide, retourner une liste vide
        if (mandat.getMemberMandatRoles() == null || mandat.getMemberMandatRoles().isEmpty()) {
            return new ArrayList<>();
        }

        // Récupérer la liste des membres associés à ce mandat
        return mandat.getMemberMandatRoles().stream()
                .map(MemberMandatRole::getMember)
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> getMemberWithRoles(Long MandatId) {
        // Récupérer les rôles des membres pour un mandat spécifique
        List<MemberMandatRole> roles = mandatRepository.findMandatById(MandatId);

        // Récupérer les membres à partir des rôles et les retourner
        return roles.stream()
                .map(MemberMandatRole::getMember)
                .collect(Collectors.toList());

    }


    @Override
    public Page<Mandat> GetMandatByDate(LocalDate dateDebut, Pageable pageable) {

        if(dateDebut != null && dateDebut.isAfter(LocalDate.now())){
            return mandatRepository.findByDateDebutContaining(dateDebut,pageable);
        }
        return mandatRepository.findAll(pageable);
    }
}
