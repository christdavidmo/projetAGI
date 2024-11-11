package com.example.amicale.Security.Data.Fixtures;

import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Security.Services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

//@Component
@RequiredArgsConstructor
public class RoleFixtures implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        List<String> Roles= Arrays.asList( "Responsable_Com", "Responsable_Pedagie",
                                            "Responsable_Sport","Responsable_Trésorie",
                                            "President","Vice-President", "Secretaire-Génerale",
                                            "MemberCommunity","OfficeMember");

        Roles.forEach(role -> {
            if(roleRepository.findByRolename(role) == null) {
                Role newRole = new Role(role);
                roleRepository.save(newRole);
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
