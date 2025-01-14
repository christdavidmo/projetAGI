package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.*;
import com.example.amicale.Data.Repository.MandatRepository;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Services.*;
import com.example.amicale.Security.Services.SecurityService;
import com.example.amicale.web.controller.officeController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class officeControllerImpl implements officeController {


    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EvenementService evenementService;
    private final MandatService mandatService;
    private final SecurityService securityService;
    private final MemberMandatRoleServices memberMandatRoleServices;
    private final RoleRepository roleRepository;
    private final PhotoServices  photoServices;


    @Override
    public String accueiloffice() {
        //return "layout/navbar/navbarOffice";
        return "office/homeoffice";
    }

    @Override
    public String gestionoffice() {
        return "office/gestion";
    }



    @Override
    public String Presidentaccueil() {
        return "president/homePresident";
       //return "layout/navbar/navbarPresident" DetailgestionPresident;
    }

    @Override
    public String Presidentgestionoffice(Model model) {
        return "president/gestionPresident";
    }




    @Override
    public String Presidentmodifiermember(Model model,
                                          Long id,
                                          String nom,
                                          String prenoms,
                                          String sexe,
                                          String email,
                                          String matricule) {

        Member member = memberRepository.findMemberById(id);
        if (member != null) {
            member.setNom(nom);
            member.setPrenoms(prenoms);
            member.setSexe(sexe);
            member.setEmail(email);
            member.setMatricule(matricule);
            memberRepository.save(member);
        }
        return "redirect:/president/gestion/detail";
    }

    @Override
    public String PresidentgestionMember(Model model
            ,@RequestParam(defaultValue = "1") int page
            ,@RequestParam(defaultValue = "8") int size
            ,@RequestParam(required = false) String Nom) {

        Page<Member> members = memberService.getMemberFilter(Nom, PageRequest.of(page -1,size));

        model.addAttribute("members", members.getContent());
        model.addAttribute("pages", members.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("Nom", Nom);

        return "president/gestionMemberPresident";
    }

    @Override
    public String PresidentgestionSaveMember(Model model
                                            ,String nom
                                            , String prenoms
                                            , String sexe
                                            , String email
                                            , String matricule) {

        Member memberExisting = memberRepository.findMemberByNom(nom);
        Role role_par_defaut = securityService.getRoleByName("MemberCommunity");

        if (memberExisting != null) {
            model.addAttribute("error, ce nom d'utilisateur existe déja");
            return "redirect:/president/gestion/detail";
        }

            Member member = new Member();

            member.setNom(nom);
            member.setPrenoms(prenoms);
            member.setSexe(sexe);
            member.setEmail(email);
            member.setMatricule(matricule);

            member.setLogin(member.getMatricule());
            member.setPassword(passwordEncoder.encode("passer"));
            member.setActive(true);
            member.setRole(role_par_defaut);
            memberRepository.save(member);


        return "redirect:/president/gestion/detail";
    }

    @Override
    public String toogleMemberStatus(Long id) {
        memberService.toogleMemberStatus(id);
        return "redirect:/president/gestion/detail";
    }


    @Override
    public ResponseEntity<?> Presidentdelete(Model model, Long id) {
       try{
           memberService.deleteMember(id);
           return ResponseEntity.ok().build();

       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du membre");
       }
    }


    @Override
    public String Presidentparametreoffice() {
        return "public/test";
    }

    @Override
    public String PresidentcontenuEvensoffice(Model model,
                                              int page,
                                              int size,
                                              String name) {
        Page<Evenement> evenements = evenementService.getFilterEvenement(name,PageRequest.of(page -1,size));
        model.addAttribute("evenements", evenements.getContent());
        model.addAttribute("pages", evenements.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("nom", name);

        return "president/contenuEvensPresident";
    }

    @Override
    public String PresidentDetailcontenuEvensoffice(Model model, Long idEvenement) {
        Evenement event = evenementService.getEvenementById(idEvenement);
        List<Photo> images = photoServices.getAllImagesToEvenement( event.getId());

        model.addAttribute("evenement", event);
        model.addAttribute("images", images);

        return "president/detailsContenuEvensPresident";
    }

    @Override
    public String PresidentMandat(Model model,
                                  int page,
                                  int size,
                                  LocalDate datedebut) {

        Page<Mandat> mandats = mandatService.GetMandatByDate(datedebut, PageRequest.of(page-1,size));
        model.addAttribute("mandats", mandats.getContent());
        model.addAttribute("pages", mandats.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("nom", mandats.getTotalElements());
        model.addAttribute("datedebut", datedebut);

        return "president/gestionMandat";
    }

    @Override
    public String PresidentModificationMandat(Model model, Long idMandat) {


        List<Member> AllMembers = memberService.getAllMembers();
        // Récupérer tous les rôles disponibles
        List<Role> roles = roleRepository.findAll();


        // Récupérer les membres et leurs rôles pour ce mandat
        List<MemberMandatRole> membersWithRoles = memberMandatRoleServices.getMembersWithRolesByMandat(idMandat);

        // Passer les rôles, le mandat et les membres avec leurs rôles à la vue
        model.addAttribute("AllRoles", roles);
        model.addAttribute("mandat", idMandat);
        model.addAttribute("AllMembers", AllMembers);
        model.addAttribute("membersWithRoles", membersWithRoles);



        return "president/modificationMandatPresident";
    }

    @Override
    public String PresidentAddMandat(Model model) {
        Mandat mandat = new Mandat();
        model.addAttribute("mandat", mandat);
        return "president/ajoutMandatPresident";
    }





}
