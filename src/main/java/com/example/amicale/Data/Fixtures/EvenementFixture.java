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

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Order(4)
@Component
@RequiredArgsConstructor
public class EvenementFixture implements CommandLineRunner {

    private final EvenementService evenementService;
    private final EvenementRepository evenementRepository;
    private  final MemberRepository memberRepository;
    private final UsersRepository UsersRepository;
    private final UsersRepository usersRepository;


    @Override

    public void run(String... args) throws Exception {


        // Récupérer tous les utilisateurs avec le rôle Responsable_Communication
        List<Users> usersWithRole = usersRepository.findByRole_Rolename("Responsable_Communication");

        if (usersWithRole.isEmpty()) {
            System.out.println("Aucun utilisateur avec le rôle OfficeMember trouvé.");


        } else {

            List<String> photoName = List.of(
                    "student1.jpg","student3.jpg",
                    "equipe2.jpg","equipe3.jpg",
                    "student.jpg","student5.jpg","student4.jpg"
            );

            // Créer des événements pour chaque membre avec le rôle Responsable_Communication
            for (int i = 0; i <= 10; i++) {
                Evenement ev = new Evenement();
                ev.setTitle("AG de rentrée" + i);
                ev.setDatePublication(LocalDate.parse("2023-03-19"));
                ev.setLieu("Amphiteatre MADIBA");
                ev.setDescription("discuter des prochaines échéances");

                // Associer un membre avec le rôle Responsable_Communication à l'événement
                for (Users user : usersWithRole) {
                    Member member = (Member) user; // Convertir Users en Member
                    ev.setCreateur(member);
                    ev.setAuthor(member.getNom());

                    for(String p : photoName) {
                        String photopath = "/images/"+p;
                        Photo photo = new Photo(photopath,ev);
                        ev.getImages().add(photo);

                    }



                    // Enregistrer l'événement
                    evenementService.saveEvenement(ev);
                    System.out.println("Événement créé : " + ev.getTitle());
                }
            }

        }
    }
}

