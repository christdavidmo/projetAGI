package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import com.example.amicale.Data.Entity.Ressources;
import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Data.Enumeration.TypeRessource;
import com.example.amicale.Data.Repository.MemberMandatRoleRepository;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.RessourcesRepository;
import com.example.amicale.Data.Repository.RessourcesRepository;
import com.example.amicale.Data.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

//@Component
@RequiredArgsConstructor
public class RessourcesFixtures implements CommandLineRunner {


    private final MemberRepository memberRepository;
    private final MemberMandatRoleRepository mandatRoleRepository;
    private final RessourcesRepository ressourceRepository;
    private  final UsersRepository usersRepository;

    @Override
    public void run(String... args) throws Exception {

        List<MemberMandatRole> members = mandatRoleRepository.findAll();

        List<String> RESnames = List.of("angular_cours.pdf",
                "droit_constitutionnelle.pdf","Excel_Debutant.pdf",
                "Initiation-à-la-photographie.pdf","pdfAlgo.pdf");

        // Récupérer tous les utilisateurs avec le rôle Responsable_Communication
        List<Users> usersWithRole = usersRepository.findByRole_Rolename("Responsable_Communication");




        // Création et ajout des ressources
        for (int i = 1; i <= 4; i++) {
            // Créer une nouvelle ressource pour chaque itération
            Ressources ressources1 = new Ressources();
            ressources1.setTitle("Ressource " + i);
            ressources1.setDatePublication(LocalDate.parse("2023-03-19"));
            ressources1.setDescription("Ressource programme " + i);


            for (Users user : usersWithRole) {
                Member member = (Member) user; // Convertir Users en Member
                ressources1.setAuthor(member.getNom());


                // Ajouter les ressources à partir des noms dans RESnames
                for (String R : RESnames) {
                    String rePath = "/ressources/" + R;
                    ressources1.setType(TypeRessource.PDF);
                    ressources1.setPath(rePath);

                    // Sauvegarde de la ressource dans la base de données
                    ressourceRepository.save(ressources1);
                    System.out.println("Ressource enregistrée: " + ressources1.getTitle());
                }

            }
        }
    }
}

