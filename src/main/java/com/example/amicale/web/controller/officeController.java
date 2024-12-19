package com.example.amicale.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                                          ,@RequestParam("nom") String nom
                                          ,@RequestParam("prenoms") String prenoms
                                          ,@RequestParam("sexe") String sexe
                                          ,@RequestParam("email") String email
                                          ,@RequestParam("matricule") String matricule);

    @GetMapping("/president/gestion/detail")
    public String Presidentgestiondetail(Model model
                                        ,@RequestParam(defaultValue = "1") int page
                                        ,@RequestParam(defaultValue = "8") int size
                                        ,@RequestParam(required = false) String Nom );

    @PostMapping("/president/gestion/detail/save")
    public String PresidentgestiondetailSave(Model model
                                            ,@RequestParam("nom") String nom
                                            ,@RequestParam("prenoms") String prenoms
                                            ,@RequestParam("sexe") String sexe
                                            ,@RequestParam("email") String email
                                            ,@RequestParam("matricule") String matricule);

    @PostMapping("/president/toogleblock/{id}")
    public String toogleMemberStatus(@PathVariable("id") Long id);

    @PostMapping("/president/delete/{id}")
    public ResponseEntity <?> Presidentdelete(Model model, @PathVariable("id") Long id);

    @GetMapping("/president/parametre")
    public String Presidentparametreoffice();

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