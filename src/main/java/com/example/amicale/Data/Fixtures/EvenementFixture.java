package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.Photo;
import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Data.Repository.EvenementRepository;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.UsersRepository;
import com.example.amicale.Data.Services.EvenementService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Order(4)
@Component
@RequiredArgsConstructor
public class EvenementFixture implements CommandLineRunner {

    private final EvenementService evenementService;
    private final EvenementRepository evenementRepository;
    private final MemberRepository memberRepository;
    private final UsersRepository usersRepository;

    // Liste des images à ajouter aux événements
    private final List<String> photoNames = List.of(
            "student1.jpg", "student3.jpg", "equipe2.jpg", "equipe3.jpg",
            "student.jpg", "student5.jpg", "student4.jpg"
    );

    @Override
    public void run(String... args) throws Exception {
        // Récupérer les utilisateurs ayant le rôle 'Responsable_Communication'
        List<Users> usersWithRole = usersRepository.findByRole_Rolename("Responsable_Communication");

        if (usersWithRole.isEmpty()) {
            System.out.println("Aucun utilisateur avec le rôle Responsable_Communication trouvé.");
        } else {
            // Créer des événements pour chaque utilisateur ayant le rôle Responsable_Communication
            usersWithRole.forEach(user -> createEvenementsForUser(user));
        }
    }

    // Créer les événements pour un utilisateur
    private void createEvenementsForUser(Users user) {
        // Créer des événements pour ce membre
        for (int i = 0; i <= 10; i++) {
            Evenement evenement = buildEvenement(user, i);
            saveEvenementWithPhotos(evenement);
        }
    }

    // Construire un événement de manière générique
    private Evenement buildEvenement(Users user, int index) {
        Evenement evenement = new Evenement();
        evenement.setTitle("AG de rentrée " + index);
        evenement.setDatePublication(LocalDate.parse("2023-03-19"));
        evenement.setLieu("Amphiteatre MADIBA");
        evenement.setDescription("Discuter des prochaines échéances");

        // Associer le membre à l'événement
        Member member = (Member) user; // Convertir Users en Member
        evenement.setCreateur(member);
        evenement.setAuthor(member.getNom());

        return evenement;
    }

    // Enregistrer l'événement avec les photos associées
    private void saveEvenementWithPhotos(Evenement evenement) {
        // Ajouter les photos à l'événement
        photoNames.forEach(photoName -> {
            Photo photo = new Photo(photoName, evenement);
            evenement.getImages().add(photo);
        });

        // Sauvegarder l'événement
        evenementService.saveEvenement(evenement);
        System.out.println("Événement créé : " + evenement.getTitle());
    }
}
