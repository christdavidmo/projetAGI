package com.example.amicale.Data.Fixtures;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Enumeration.Statut;
import com.example.amicale.Data.Repository.MandatRepository;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Order(3)
//@Component
@AllArgsConstructor
public class MandatFixture implements CommandLineRunner {

    private final MandatRepository mandatRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional // Ajout de cette annotation pour gérer la transaction
    public void run(String... args) throws Exception {

        // Initialisation des rôles
        List<Role> lesroles = roleRepository.findAll();

        Role presidentRole = null;
        Role vicePresidentRole = null;
        Role responsableCommunicationRole = null;
        Role responsableTresorieRole = null;
        Role responsableSportRole = null;
        Role SecretaireGeneraleRole = null;

        for (Role role : lesroles) {
            System.out.println(role);

            switch (role.getRolename()) {
                case "President":
                    presidentRole = role;
                    break;
                case "Vice_President":
                    vicePresidentRole = role;
                    break;
                case "Responsable_Communication":
                    responsableCommunicationRole = role;
                    break;
                case "Responsable_Tresorie":
                    responsableTresorieRole = role;
                    break;
                case "Responsable_Sport":
                    responsableSportRole = role;
                    break;
                case "Secretaire_Generale":
                    SecretaireGeneraleRole  = role;
                    break;
                default:
                    break;
            }
        }

        if (presidentRole == null || vicePresidentRole == null || responsableCommunicationRole == null ||
                responsableTresorieRole == null || responsableSportRole == null) {
            System.out.println("Un ou plusieurs rôles nécessaires n'ont pas été trouvés!");
            return;
        }

        // Création des membres avec les rôles
        Member president = new Member();
        president.setNom("President2023");
        president.setPrenoms("prenoms"+president.getNom());
        president.setMatricule("P"+president.getNom());
        president.setEmail("president"+president.getNom()+"@gmail.com");
        president.setSexe("masculin");
        president.setDateOfJoining(LocalDate.parse("2023-02-19"));
        president.setLogin("president2020");
        president.setPassword(passwordEncoder.encode("passer"));
        president.setActive(true);
        president.setStatut(Statut.ACTIF);
        president.setRole(presidentRole);
        memberRepository.save(president);

        Member membre1 = new Member();
        membre1.setNom("vicepresident2023");
        membre1.setPrenoms("prenoms"+membre1.getNom());
        membre1.setMatricule("VP"+membre1.getNom());
        membre1.setEmail("vicepresident"+membre1.getNom()+"@gmail.com");
        membre1.setSexe("masculin");
        membre1.setDateOfJoining(LocalDate.parse("2023-02-13"));
        membre1.setLogin("VP"+membre1.getNom());
        membre1.setPassword(passwordEncoder.encode("passer"));
        membre1.setActive(true);
        membre1.setStatut(Statut.ACTIF);
        membre1.setRole(vicePresidentRole);
        memberRepository.save(membre1);

        Member membre2 = new Member();
        membre2.setNom("Membre22023");
        membre2.setPrenoms("prenoms"+membre2.getNom());
        membre2.setMatricule("M1234"+membre2.getNom());
        membre2.setEmail("membre2"+membre2.getNom()+"@gmail.com");
        membre2.setSexe("masculin");
        membre2.setDateOfJoining(LocalDate.parse("2023-01-04"));
        membre2.setLogin("membre2"+membre2.getNom());
        membre2.setPassword(passwordEncoder.encode("passer"));
        membre2.setActive(true);
        membre2.setStatut(Statut.ACTIF);
        membre2.setRole(responsableCommunicationRole);
        memberRepository.save(membre2);

        Member membre3 = new Member();
        membre3.setNom("Membre32023");
        membre3.setPrenoms("prenoms"+membre3.getNom());
        membre3.setMatricule("M1234");
        membre3.setEmail("membre3@gmail.com");
        membre3.setSexe("masculin");
        membre3.setDateOfJoining(LocalDate.parse("2023-02-14"));
        membre3.setLogin("membre3"+membre3.getNom());
        membre3.setPassword(passwordEncoder.encode("passer"));
        membre3.setActive(true);
        membre3.setStatut(Statut.ACTIF);
        membre3.setRole(responsableTresorieRole);
        memberRepository.save(membre3);

        Member membre4 = new Member();
        membre4.setNom("Membre42023");
        membre4.setPrenoms("prenoms"+membre4.getNom());
        membre4.setMatricule("M1234"+membre4.getNom());
        membre4.setEmail("membre4@gmail.com");
        membre4.setSexe("masculin");
        membre4.setDateOfJoining(LocalDate.parse("2023-02-14"));
        membre4.setLogin("membre4"+membre4.getNom());
        membre4.setPassword(passwordEncoder.encode("passer"));
        membre4.setActive(true);
        membre4.setStatut(Statut.ACTIF);
        membre4.setRole(responsableSportRole);
        memberRepository.save(membre4);


        Member membre5 = new Member();
        membre5.setNom("Membre52023");
        membre5.setPrenoms("prenoms"+membre5.getNom());
        membre5.setMatricule("M12345"+membre4.getNom());
        membre5.setEmail("membre5@gmail.com");
        membre5.setSexe("masculin");
        membre5.setDateOfJoining(LocalDate.parse("2023-02-14"));
        membre5.setLogin("membre5"+membre4.getNom());
        membre5.setPassword(passwordEncoder.encode("passer"));
        membre5.setActive(true);
        membre5.setStatut(Statut.ACTIF);
        membre5.setRole(SecretaireGeneraleRole);
        memberRepository.save(membre5);


        // Création du Mandat
        Mandat mandat = new Mandat();

        // creation du memberrole du mandat
        MemberMandatRole memberMandatRole1 = new MemberMandatRole();
        memberMandatRole1.setMember(president);
        memberMandatRole1.setRole(presidentRole);
        memberMandatRole1.setMandat(mandat);

        MemberMandatRole memberMandatRole2 = new MemberMandatRole();
        memberMandatRole2.setMember(membre1);
        memberMandatRole2.setRole(membre1.getRole());
        memberMandatRole2.setMandat(mandat);

        MemberMandatRole memberMandatRole3 = new MemberMandatRole();
        memberMandatRole3.setMember(membre3);
        memberMandatRole3.setRole(membre3.getRole());
        memberMandatRole3.setMandat(mandat);


        MemberMandatRole memberMandatRole4 = new MemberMandatRole();
        memberMandatRole4.setMember(membre4);
        memberMandatRole4.setRole(membre4.getRole());
        memberMandatRole4.setMandat(mandat);



        MemberMandatRole memberMandatRole5 = new MemberMandatRole();
        memberMandatRole5.setMember(membre5);
        memberMandatRole5.setRole(membre5.getRole());
        memberMandatRole5.setMandat(mandat);




        mandat.setPresident(president);
        mandat.setMemberMandatRoles(Arrays.asList( memberMandatRole1,memberMandatRole2 ,memberMandatRole3 , memberMandatRole4, memberMandatRole5));
        mandat.setDateDebut(LocalDate.parse("2023-10-14"));
        mandat.setDateFin(LocalDate.parse("2024-10-20"));
        mandat.setActive(true);
        mandatRepository.save(mandat);
    }
}
