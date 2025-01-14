package com.example.amicale.web.controller;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Security.Services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

public interface officeController {

    @GetMapping("/office/accueil")
    public String accueiloffice();

    @GetMapping("/office/gestion")
    public String gestionoffice();


    @GetMapping("/president/accueil")
    public String Presidentaccueil();

    @GetMapping("/president/gestion")
    public String Presidentgestionoffice(Model model);

    @PostMapping("/president/modifier/member")
    public String Presidentmodifiermember(Model model,
                                          @RequestParam("id") Long id
            , @RequestParam("nom") String nom
            , @RequestParam("prenoms") String prenoms
            , @RequestParam("sexe") String sexe
            , @RequestParam("email") String email
            , @RequestParam("matricule") String matricule);

    @GetMapping("/president/gestion/detail")
    public String PresidentgestionMember(Model model
            , @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "8") int size
            , @RequestParam(required = false) String Nom);

    @PostMapping("/president/gestion/detail/save")
    public String PresidentgestionSaveMember(Model model
            , @RequestParam("nom") String nom
            , @RequestParam("prenoms") String prenoms
            , @RequestParam("sexe") String sexe
            , @RequestParam("email") String email
            , @RequestParam("matricule") String matricule);

    @PostMapping("/president/toogleblock/{id}")
    public String toogleMemberStatus(@PathVariable("id") Long id);

    @PostMapping("/president/delete/{id}")
    public ResponseEntity<?> Presidentdelete(Model model, @PathVariable("id") Long id);

    @GetMapping("/president/parametre")
    public String Presidentparametreoffice();


    @GetMapping("/president/contenu/evenement")
    public String PresidentcontenuEvensoffice(Model model,
                                              @RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "6") int size,
                                              @RequestParam(required = false) String name);

    @GetMapping("/president/contenu/evenement/{idEvenement}/detail")
    public String PresidentDetailcontenuEvensoffice(Model model, @PathVariable("idEvenement") Long id_evEnement);

    @GetMapping("/president/mandat")
    public String PresidentMandat(Model model,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "6") int size,
                                  @RequestParam(required = false) LocalDate datedebut);
    @GetMapping("/president/mandat/form")
    public String PresidentAddMandat(Model model);

    @GetMapping("/president/mandat/{idMandat}/modification")
    public String PresidentModificationMandat(Model model,@PathVariable("idMandat") Long idMandat);



}

/*
* @Override
    public String Presidentgestionoffice(Model model
                                         ,@RequestParam(defaultValue = "1") int page
                                         ,@RequestParam(defaultValue = "5") int size
                                            ,@RequestParam(required = false) String Nom) {

        Page<Member> members = memberService.getMemberFilter(Nom, PageRequest.of(page -1,size));

        model.addAttribute("members", members.getContent());
        model.addAttribute("pages", members.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("Nom", Nom);

        return "president/gestionPresident";
    } */