package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.MemberCommunity;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.MemberCommunityRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Services.MemberCommunityService;
import com.example.amicale.Security.Services.SecurityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date; // Import nécessaire
import java.util.Calendar;

//@Component
@RequiredArgsConstructor
public class MemberCommunityFixtures implements CommandLineRunner {

    private final MemberCommunityService memberCommunityService;
    private  final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final SecurityService securityService;
    private final MemberCommunityRepository memberCommunityRepository;


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <=11 ; i++) {

            MemberCommunity mc = new MemberCommunity();

            //parametres d'identités
            mc.setNom("membre"+i);
            mc.setPrenoms("prenoms"+i);

            //sexe
            String S = (i % 2 == 0) ? "Masculin" : "Feminin" ;
            mc.setSexe(S);

            //l'e-mail
            mc.setEmail("membre"+i+"@gmail.com");
            mc.setMatricule("Agi"+mc.getNom()+i);

            // la date , Créer une date d'adhésion à partir de la date actuelle
            mc.setDateOfJoining(new Date(System.currentTimeMillis())); // Utiliser System.currentTimeMillis() pour obtenir la date actuelle

            //le role
            Role role = roleRepository.findByRolename("MemberCommunity");
            if (role != null) {
                mc.setRole(role);
            } else {
                // Gérer le cas où le rôle n'existe pas
                throw new IllegalStateException("Ce rôle  n'existe pas !");
            }

            //le login
            mc.setLogin("membre"+i);

            //le password
            mc.setPassword(passwordEncoder.encode( "membre1234"));

            //active
            mc.setActive(true);

            memberCommunityRepository.save(mc);
        }
    }
}
