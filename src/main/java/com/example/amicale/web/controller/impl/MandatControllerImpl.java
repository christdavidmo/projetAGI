package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.MandatRepository;
import com.example.amicale.Data.Repository.MemberMandatRoleRepository;
import com.example.amicale.Data.Services.MandatService;
import com.example.amicale.Data.Services.MemberMandatRoleServices;
import com.example.amicale.Data.Services.MemberService;
import com.example.amicale.Security.Services.SecurityService;
import com.example.amicale.web.controller.MandatController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate; // Import de LocalDate

@Controller
@RequiredArgsConstructor
public class MandatControllerImpl implements MandatController {

    private final MemberService memberService ;
    private final MandatService mandatService ;
    private final MandatRepository mandatRepository ;
    private final SecurityService securityService ;
    private  final MemberMandatRoleRepository membermandatRoleRepository ;
    private final MemberMandatRoleServices memberMandatRoleServices ;

    @Override
    public String  FormulaireMandat(Model model) {

        //recupere d'abord tous les membres
        List<Member> members = memberService.getAllMembersByActiveTrue();

        //Tous les roles
        List<Role> roles = securityService.getAllRoles();

        //liste filtrée
        List<Role> rolesFilters = new ArrayList<Role>();

        for (Role role : roles) {
            if(     !role.getRolename().equals("MemberCommunity") &&
                    !role.getRolename().equals("OfficeMember") &&
                    !role.getRolename().equals("President") ){
                rolesFilters.add(role);
            }
        }

        //créer un mandat
        Mandat mandat = new Mandat();

        model.addAttribute("mandat",mandat);
        model.addAttribute("roles",rolesFilters);
        model.addAttribute("members", members);

        return "mandat/ajouterMandat";
    }

    @Override
    public String enregistrementMandat(Map<String, String> formData) {
        // Extraire les données du formulaire
        String dateDebut = formData.get("Date_debut");
        String dateFin = formData.get("Date_Fin");

        // Récupérer les IDs des membres pour chaque rôle
        Long presidentId = Long.valueOf(formData.get("president-nom"));
        Long vicePresidentId = Long.valueOf(formData.get("vice-president-nom"));
        Long poleCommunicationId = Long.valueOf(formData.get("Pole-Communication-nom"));
        Long polePedagogieId = Long.valueOf(formData.get("Pole-pedagogie-nom"));
        Long poleSportId = Long.valueOf(formData.get("Pole-sport-nom"));
        Long poleTresorieId = Long.valueOf(formData.get("Pole-Tresorie-nom"));
        Long sgId = Long.valueOf(formData.get("sg-nom"));

        // Charger les membres à partir de leurs IDs
        Member president = memberService.getMemberById(presidentId);
        Member vicePresident = memberService.getMemberById(vicePresidentId);
        Member poleCommunication = memberService.getMemberById(poleCommunicationId);
        Member polePedagogie = memberService.getMemberById(polePedagogieId);
        Member poleSport = memberService.getMemberById(poleSportId);
        Member poleTresorie = memberService.getMemberById(poleTresorieId);
        Member sg = memberService.getMemberById(sgId);

        // Créer un nouveau mandat
        Mandat mandat = new Mandat();
        mandat.setDateDebut(Date.valueOf(dateDebut).toLocalDate());  // Convertir la date de début
        mandat.setDateFin(Date.valueOf(dateFin).toLocalDate());      // Convertir la date de fin

        mandat.setPresident(president);

        // Récupérer les rôles à partir du service
        Role roleVicePresident = securityService .getRoleByName("Vice_President");
        Role roleResponsableCommunication =securityService .getRoleByName("Responsable_Communication");
        Role roleResponsablePedagogie = securityService .getRoleByName("Responsable_Pedagogie");
        Role roleResponsableSport = securityService .getRoleByName("Responsable_Sport");
        Role roleResponsableTresorie = securityService .getRoleByName("Responsable_Tresorie");
        Role roleSecretaireGenerale = securityService .getRoleByName("Secretaire_Generale");

        // Créer les objets MemberMandatRole avec les rôles récupérés
        List<MemberMandatRole> memberMandatRoles = new ArrayList<>();
        memberMandatRoles.add(new MemberMandatRole(vicePresident,mandat, roleVicePresident));
        memberMandatRoles.add(new MemberMandatRole(poleCommunication,mandat,  roleResponsableCommunication));
        memberMandatRoles.add(new MemberMandatRole(polePedagogie,  mandat,roleResponsablePedagogie));
        memberMandatRoles.add(new MemberMandatRole(poleSport, mandat, roleResponsableSport));
        memberMandatRoles.add(new MemberMandatRole(poleTresorie, mandat, roleResponsableTresorie));
        memberMandatRoles.add(new MemberMandatRole(sg,mandat,  roleSecretaireGenerale));

        // Sauvegarder les MemberMandatRole dans la base de données
        membermandatRoleRepository.saveAll(memberMandatRoles);


        // Enregistrer le mandat dans la base de données
        mandatService.saveMandat(mandat);

        // Retourner une réponse (par exemple, rediriger vers une page de confirmation)
        return "redirect:/mandat/success";  // Ou une page de succès
    }


}
