package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.Photo;
import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Data.Repository.EvenementRepository;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.UsersRepository;
import com.example.amicale.Data.Services.EvenementService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Order(4)
//@Component
@RequiredArgsConstructor
public class EvenementFixture implements CommandLineRunner {

    private final EvenementService evenementService;
    private final EvenementRepository evenementRepository;
    private final MemberRepository memberRepository;
    private final UsersRepository usersRepository;


    @Override
    public void run(String... args) throws Exception {


        //liste des images
        List<String> images = List.of(
                "student1.jpg",
                "student3.jpg",
                "student4.jpg",
                "student5.jpg");


        for (int i = 1; i <=6 ; i++) {

            List<Member> members =  memberRepository.findAll();

            Evenement evenement = new Evenement();
            evenement.setTitle("evenement"+i);
            evenement.setDescription("description"+i);
            evenement.setLieu("lieu"+i);
            evenement.setDatePublication(LocalDate.now());

            Member createur = members.get(i % members.size()); //prendre un membre au hasard
            evenement.setCreateur(createur);
            evenement.setAuthor(createur.getNom());


            for (String image : images) {
                Photo photo = new Photo();
                photo.setPath(image);
                photo.setEvenement(evenement);
                evenement.getImages().add(photo);
            }

            evenementRepository.save(evenement);
        }


       /* @Column(nullable = false)
        protected String title;

        @Column(nullable = false)
        protected String author;

        protected LocalDate datePublication ;

        private String Lieu;

        private String Description;


        @OneToMany(mappedBy = "evenement",cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Photo> images = new ArrayList<>() ;*/
    }
}
