package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Enumeration.Statut;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Security.Services.SecurityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Order(2)

//@Component
@RequiredArgsConstructor
public class MemberFixtures implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final SecurityService securityService;



    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Role memberCommunityRole = roleRepository.findByRolename("MemberCommunity");

        if (memberCommunityRole == null) {
            System.out.println("Role 'OfficeMember' not found!");
            return;
        }

        // Récupérer l'objet Role de manière transactionnelle
        memberCommunityRole = roleRepository.getReferenceById(memberCommunityRole.getId());


        for (int i = 0; i <= 5; i++) {
            Member member = new Member();

            member.setNom("NAME" + i);
            member.setPrenoms("prenoms" + i);
            member.setEmail("email" + i);

            // Créer une date d'adhésion à partir de la date actuelle
            member.setDateOfJoining(LocalDate.now());

            String sexe = (i % 2 == 0) ? "masculin" : "feminin";
            member.setSexe(sexe);

            member.setMatricule("matricule" + i + member.getNom());
            member.setRole(memberCommunityRole); // Assigner le rôle

            // Le login et le mot de passe
            member.setLogin("membre" + i);
            member.setPassword(passwordEncoder.encode("passer"));

            member.setActive(true);
            member.setStatut(Statut.ACTIF);
            memberRepository.save(member);  // Enregistrer le membre dans la base de données
        }
    }
}

/* @Column(nullable = false)
    private String Sexe ;
    private Date dateOfJoining;

    @Column(nullable = false)
    private String Nom ;

    @Column(nullable = false)
    private String Prenoms;


    private String Email ;

    @Column(nullable = false)
    private String Matricule;


    @ManyToMany(mappedBy = "members")
    private List<Mandat> mandats;

    @OneToMany(mappedBy = "createur" , fetch = FetchType.LAZY)
    private List<Evenement> evenements;

    @OneToMany(mappedBy = "createurA", fetch = FetchType.LAZY)
    private List<Actualite> actualites ; */