package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.OfficeMember;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Services.OfficeMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

//@Component
@RequiredArgsConstructor
public class OfficeMemberFixture implements CommandLineRunner {

    private final OfficeMemberService officeMemberService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i <=5 ; i++) {
            List<Role> roles = roleRepository.findAll();

            OfficeMember om = new OfficeMember();
             om.setNom("officeMember"+i);
             om.setPrenoms("Prenom"+i);

             om.setEmail("officeMember"+i+"@gmail.com");
             om.setMatricule("Agi"+om.getNom()+"20"+i);

            // la date , Créer une date d'adhésion à partir de la date actuelle
            om.setDateOfJoining(new Date(System.currentTimeMillis())); // Utiliser System.currentTimeMillis() pour obtenir la date actuelle

             String S = ( i % 2 == 0) ? "Masculin" :"Feminin";
             om.setSexe(S);

             om.setLogin("officeMember"+i);
             om.setPassword(passwordEncoder.encode("officeMember1234"));

             Role role = roleRepository.getByRolename("OfficeMember");
             om.setRole(role);

             om.setActive(true);




            /* Attribuer des rôles en fonction de l'indice
            if (i % 2 == 0) {
                // Indices pairs : attribuer les rôles avec ID 1 et 2
                om.setRole(roles.get(0)); // Rôle ID 1
                om.setRole(roles.get(1)); // Rôle ID 2
            } else {
                // Indices impairs : attribuer les rôles avec ID 3 et 4
                om.setRole(roles.get(2)); // Rôle ID 3
                om.setRole(roles.get(3)); // Rôle ID 4
            }*/

            // Enregistrez l'OfficeMember dans la base de données
            officeMemberService.saveOfficeMember(om);

        }

    }
}
