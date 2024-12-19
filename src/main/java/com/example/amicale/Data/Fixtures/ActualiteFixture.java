package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Repository.ActualiteRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Services.ActualiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;


//@Component
@RequiredArgsConstructor
public class ActualiteFixture implements CommandLineRunner {

    private final ActualiteRepository actualiteRepository;
    private final ActualiteService actualiteService;
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        /* Récupérer les rôles existants
        //prend le role du president , plus de liste
        List<Role> roles = roleRepository.findAll();

        for (int i = 0; i <= 11; i++) {
            Actualite ac = new Actualite();
            ac.setTitle("Atualite" + i);
            ac.setContenu("Actualite_Contenu");

            ac.setDatePublication(new Date(System.currentTimeMillis()));


            for (Role role : roles) {

                // Cherchez un OfficeMember qui a ce rôle
                //List<OfficeMember> membersWithRole = officeMemberService.getOfficeMemberByRole(role);

                if (!membersWithRole.isEmpty()) {

                    // Assignez le premier OfficeMember trouvé
                    OfficeMember om = membersWithRole.get(0);

                    // Assurez-vous que cette méthode existe dans votre classe Evenement
                    ac.setOfficeMember(om);

                    ac.setAuthor(om.getNom());

                    // Enregistrer l'événement
                    actualiteRepository.save(ac);
                    break; // Sortir de la boucle dès qu'un membre est trouvé

                }

            }

        }*/
    }

}
