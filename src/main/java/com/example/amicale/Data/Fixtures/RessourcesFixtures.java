package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.*;
import com.example.amicale.Data.Enumeration.TypeRessource;
import com.example.amicale.Data.Repository.*;
import com.example.amicale.Data.Repository.RessourcesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

//@Order(6)
//@Component
@RequiredArgsConstructor
public class RessourcesFixtures implements CommandLineRunner {


    private final MemberRepository memberRepository;
    private final MemberMandatRoleRepository mandatRoleRepository;
    private final RessourcesRepository ressourceRepository;
    private  final UsersRepository usersRepository;
    private final EcoleRepository ecoleRepository;


    public void run(String... args) throws Exception {

        List<MemberMandatRole> members = mandatRoleRepository.findAll();

        //"droit_constitutionnelle.pdf" "Initiation-à-la-photographie.pdf"
        List<String> RESnames = List.of(
                "cours-sql-sh.pdf"
                ,"Excel_Debutant.pdf"
                ,"pdfAlgo.pdf");

        // Récupérer tous les utilisateurs avec le rôle Responsable_Communication
        List<Users> usersWithRole = usersRepository.findByRole_Rolename("Responsable_Communication");

        //trouver une ecole
        Ecole ecole = ecoleRepository.findByEcoleName("Ingenieur");

        // Création et ajout des ressources
        for (int i = 1; i <= 3; i++) {
            // Créer une nouvelle ressource pour chaque itération
            Ressources ressources1 = new Ressources();
            ressources1.setTitle("Ressource " + i);
            ressources1.setDatePublication(LocalDate.parse("2023-03-19"));
            ressources1.setDescription("Ressource programme " + i);
            ressources1.setEcoles(Set.of(ecole));



                // Ajouter les ressources à partir des noms dans RESnames
                for (String R : RESnames) {
                    String rePath = R;
                    ressources1.setType(TypeRessource.PDF);
                    ressources1.setPath(rePath);

                    // Sauvegarde de la ressource dans la base de données
                    ressourceRepository.save(ressources1);
                    System.out.println("Ressource enregistrée: " + ressources1.getTitle());
                }


        }
    }
}

