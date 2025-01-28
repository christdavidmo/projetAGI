package com.example.amicale.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface PublicController {
    @GetMapping("/")
    public String publicView();

    @GetMapping("/public/aboutus")
    public String aboutusView();

    @GetMapping("/public/aboutus/more")
    public String aboutusAgiEnPlusView();

    @GetMapping("/public/activites")
    public String activitesView(Model model ,@RequestParam(defaultValue = "1") int page
                                            ,@RequestParam(defaultValue = "6") int size
                                            ,@RequestParam(required = false) String name);
    @GetMapping("/public/activites/{id}/detail")
    public String detailactivitesView(Model model ,@PathVariable Long id);

    @GetMapping("/public/contact")
    public String contactView();

    @GetMapping("/public/historique/bureau")
    public String historiqueBureauView(Model model ,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "3") int size);

    @GetMapping("/public/detail/historique/bureau/{id}")
    public String historiqueDetailBureauView(Model model,@PathVariable Long id );


    @GetMapping("/public/test")
    public String testView();

    @GetMapping("/public/error")
    public String ErrorView();

    @GetMapping("/public/ressource")
    public String ressourceView(Model model );

    @GetMapping("/public/ressource/detail")
    public String ressourceDetailView(Model model );

    @GetMapping("/public/ressource/Batiment/Droit")
    public String ressourceBatimentDroit(Model model,
                                         @RequestParam(defaultValue = "1") int page ,
                                         @RequestParam(defaultValue = "6") int size);

    @GetMapping("/public/ressource/Batiment/Ingenieur")
    public String ressourceBatimentIngenieur(Model model,
                                             @RequestParam(defaultValue = "1") int page ,
                                             @RequestParam(defaultValue = "6") int size);

    @GetMapping("/public/ressource/Batiment/Madiba")
    public String ressourceBatimentMadiba(Model model,
                                             @RequestParam(defaultValue = "1") int page ,
                                             @RequestParam(defaultValue = "6") int size);

    @GetMapping("/public/ressource/Batiment/Management")
    public String ressourceBatimentManagement(Model model,
                                             @RequestParam(defaultValue = "1") int page ,
                                             @RequestParam(defaultValue = "6") int size);
}
