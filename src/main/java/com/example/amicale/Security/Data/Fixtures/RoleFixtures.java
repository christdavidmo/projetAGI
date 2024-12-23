package com.example.amicale.Security.Data.Fixtures;

import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(1)
@Component
@RequiredArgsConstructor
public class RoleFixtures implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // Liste des rôles à insérer dans la base de données
        List<String> roles = Arrays.asList(
                "MemberCommunity","OfficeMember",
                "Responsable_Communication", "Responsable_Pedagogie",
                "Responsable_Sport", "Responsable_Tresorie",
                "President", "Vice_President", "Secretaire_Generale"

        );

        // Pour chaque rôle, vérifier s'il existe, sinon le créer et l'enregistrer
        roles.forEach(roleName -> {
            Role role = roleRepository.findByRolename(roleName); // Recherche du rôle dans la base

            // Si le rôle n'existe pas, on le crée et l'enregistre
            if (role == null) {
                role = new Role();
                role.setRolename(roleName); // Assurez-vous d'avoir un setter pour le nom du rôle
                role.setActive(true);
                roleRepository.save(role);  // Sauvegarde du rôle dans la base de données
                System.out.println("Rôle " + roleName + " ajouté.");
            }
        });

      /* if(securityService.getRoleByName("Responsable_Com") == null) {
            Role rolecom = new Role("Responsable_Com");
            securityService.saveRole(rolecom);
        }


        if(securityService.getRoleByName("Responsable_Pedagie") == null) {
            Role rolePda = new Role("Responsable_Pedagie");
            securityService.saveRole(rolePda);

        }


        if(securityService.getRoleByName("Responsable_Sport") == null) {
            Role rolePda = new Role("Responsable_Sport");
            securityService.saveRole(rolePda);

        }


        if(securityService.getRoleByName("Responsable_Trésorie") == null) {
            Role rolePda = new Role("Responsable_Trésorie");
            securityService.saveRole(rolePda);

        }

        if(securityService.getRoleByName("President") == null) {
            Role rolePda = new Role("President");
            securityService.saveRole(rolePda);

        }

        if(securityService.getRoleByName("Vice-President") == null) {
            Role rolePda = new Role("Vice-President");
            securityService.saveRole(rolePda);

        }

        if(securityService.getRoleByName("Secretaire-Genrale") == null) {
            Role rolePda = new Role("Secretaire-Génerale");
            securityService.saveRole(rolePda);

        }

        if(securityService.getRoleByName("MemberCommunity") == null) {
            Role rolePda = new Role("MemberCommunity");
            securityService.saveRole(rolePda);

        }

        if(securityService.getRoleByName("OfficeMember") == null) {
            Role rolePda = new Role("OfficeMember");
            securityService.saveRole(rolePda);

        }*/



    }
}
