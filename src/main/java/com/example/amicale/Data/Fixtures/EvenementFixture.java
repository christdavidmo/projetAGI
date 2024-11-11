package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Entity.OfficeMember;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.EvenementRepository;
import com.example.amicale.Data.Repository.OfficeMemberRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Services.EvenementService;
import com.example.amicale.Data.Services.OfficeMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

//@Component
@RequiredArgsConstructor
public class EvenementFixture implements CommandLineRunner {

    private final EvenementService evenementService;
    private final EvenementRepository evenementRepository;
    private final OfficeMemberService officeMemberService;
    private final OfficeMemberRepository officeMemberRepository;

    @Override
    public void run(String... args) throws Exception {

        // Récupérer tous les membres du bureau
        List<OfficeMember> membersWithRole = officeMemberRepository.findAll();

        // Vérifier si des membres existent dans la base de données
        if (membersWithRole.isEmpty()) {
            System.out.println("Aucun membre du bureau trouvé ! Aucun événement ne sera créé.");
            return; // Si pas de membre, on quitte la méthode
        } else {
            System.out.println("Membres du bureau trouvés : " + membersWithRole.size());
        }

        // Créer des événements
        for (int i = 0; i <= 20; i++) {
            Evenement ev = new Evenement();
            ev.setTitle("AG De Rentrée " + i);
            ev.setDatePublication(new Date(System.currentTimeMillis())); // Date actuelle
            ev.setLieu("Amphiteatre MADIBA");
            ev.setDescription("Causerie sur les prochaines échéances");

            // Associer un OfficeMember à l'événement
            OfficeMember om = membersWithRole.get(0); // Prendre le premier membre de la liste
            ev.setOfficeMember(om);
            ev.setAuthor(om.getNom());

            // Enregistrer l'événement
            evenementService.saveEvenement(ev);

            // Log pour indiquer que l'événement a été créé
            System.out.println("Événement créé : " + ev.getTitle());
        }
    }
}
