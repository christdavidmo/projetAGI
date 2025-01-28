package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.*;
import com.example.amicale.Data.Services.*;
import com.example.amicale.web.controller.PublicController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicControllerImpl implements PublicController {

    private final EvenementService evenementService;
    private final MandatService mandatService;
    private final PhotoServices photoServices;
    private final RessourceServices ressourceServices;
    private final EcoleServices ecoleServices;
    private final RessourceServices ressourceService;


    @Override
    public String publicView() {
        return "public/homepage";
        // return "layout/navbar/navbar";
    }

    @Override
    public String aboutusView() {
        return "public/us/aboutus";
        // return "layout/navbar/navbar";
    }

    @Override
    public String aboutusAgiEnPlusView() {
        return "public/agienplus/agiDance";
    }

    @Override
    public String activitesView(Model model
                                ,@RequestParam(defaultValue = "1") int page
                                ,@RequestParam(defaultValue = "6") int size
                                ,@RequestParam(required = false) String name) {


        //recupère d'abord tous les elements sosus forme d'une liste
        Page<Evenement> evenements = evenementService.getFilterEvenement(name , PageRequest.of(page -1,size));
        model.addAttribute("evenements", evenements.getContent());

        //le nombre total de page
        model.addAttribute("pages", page);

        //la page actuelle
        model.addAttribute("currentPage", page);


        //le nom de l'evenement
        model.addAttribute("name",name);

        //creer une liste pour recuperer la liste des pages à afficher
        List<Integer> pageList = new ArrayList<>();


        //recupere le nombre totale de page //
        int totalesPages = evenements.getTotalPages();

        if(totalesPages <= 4 ){
            for(int i = 1; i <= totalesPages ; i++ ){
                pageList.add(i);
            }
        }else{
            pageList.add(1);
            pageList.add(2);
            pageList.add(3);
            pageList.add(4);


            // Si on est dans la dernière moitié des pages, afficher la page suivante (5, 6...)
            if (page >= 4 && page < totalesPages- 1) {
                pageList.add(page + 1);
            }


            if(page  < totalesPages -1 ){
                pageList.add(-1);
            }


            pageList.add(totalesPages);


        }

        //le nombre total de page
        model.addAttribute("pageList",  pageList);

        return "public/activite/activite";
    }

    @Override
    public String detailactivitesView(Model model,
                                      @PathVariable Long id) {

        List<Photo> AllImages = photoServices.getAllImagesToEvenement(id);

        model.addAttribute("nbrImages", AllImages.size());
        model.addAttribute("photos", AllImages);

        return "public/detailsActivite";
    }


    @Override
    public String contactView() {
        return "public/contact/contact";
    }

    @Override
    public String historiqueBureauView(Model model ,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "3") int size) {
        Page<Mandat> lesmandats = mandatService.GetAllMandat(PageRequest.of(page -1,size));

        model.addAttribute("lesmandats", lesmandats.getContent());
        model.addAttribute("pages", lesmandats.getTotalPages());

        model.addAttribute("currentPage", page);

        return "public/historique/historique";
    }

    @Override
    public String historiqueDetailBureauView(Model model,@PathVariable Long id) {

        List<Member> members = mandatService.GetMandatById(id);
        model.addAttribute("members", members);

        return "public/historique/detailsHistorique";
    }


    @Override
    public String testView() {
       return "layout/navbar/navbar";
       // return "public/cvcheryl";
    }

    @Override
    public String ErrorView() {
        return "error/error404";
    }

    @Override
    public String ressourceView( Model model) {
        List<Ecole> ecoles = ecoleServices.getEcoles();
        model.addAttribute("ecoles", ecoles);
        return "public/ressources/ressourceAcademique";
    }

    @Override
    public String ressourceBatimentDroit(Model model, int page, int size) {
        String  name = "Droit" ;
        Page<Ressources> ressources = ressourceService.getAllRessourcesByEcole(PageRequest.of(page -1,size),name);

        model.addAttribute("ressources", ressources);

        // la page actuelle
        model.addAttribute("currentPage", page);

        model.addAttribute("ecoleName", name);

        //le nombre total de page
        model.addAttribute("pages",ressources.getTotalPages());


        // Créer une liste de pages à afficher (avec des points de suspension si nécessaire)
        List<Integer> paginationPages = new ArrayList<>();
        int totalPages = ressources.getTotalPages();

        // Pages à afficher
        if (totalPages <= 4) {
            // Si le total des pages est <= 4, afficher toutes les pages
            for (int i = 1; i <= totalPages; i++) {
                paginationPages.add(i);
            }
        } else {
            // Afficher la première page, la dernière page et les pages autour de la page courante
            paginationPages.add(1); // première page

            // Afficher 2 pages avant et après la page courante, si elles existent
            if (page > 2) {
                paginationPages.add(page - 1);
            }
            paginationPages.add(page);
            if (page < totalPages - 1) {
                paginationPages.add(page + 1);
            }

            paginationPages.add(totalPages); // dernière page
        }
        model.addAttribute("paginationPages", paginationPages);



        return "public/ressources/ressourceIng";
    }

    @Override
    public String ressourceBatimentIngenieur(Model model, int page, int size) {
        String  name = "Ingenieur" ;
        Page<Ressources> ressources = ressourceService.getAllRessourcesByEcole(PageRequest.of(page -1,size),name);

        model.addAttribute("ressources", ressources);

        // la page actuelle
        model.addAttribute("currentPage", page);

        model.addAttribute("ecoleName", name);

        //le nombre total de page
        model.addAttribute("pages",ressources.getTotalPages());


        // Créer une liste de pages à afficher (avec des points de suspension si nécessaire)
        List<Integer> paginationPages = new ArrayList<>();
        int totalPages = ressources.getTotalPages();

        // Pages à afficher
        if (totalPages <= 4) {
            // Si le total des pages est <= 4, afficher toutes les pages
            for (int i = 1; i <= totalPages; i++) {
                paginationPages.add(i);
            }
        } else {
            // Afficher la première page, la dernière page et les pages autour de la page courante
            paginationPages.add(1); // première page

            // Afficher 2 pages avant et après la page courante, si elles existent
            if (page > 2) {
                paginationPages.add(page - 1);
            }
            paginationPages.add(page);
            if (page < totalPages - 1) {
                paginationPages.add(page + 1);
            }

            paginationPages.add(totalPages); // dernière page
        }
        model.addAttribute("paginationPages", paginationPages);



        return "public/ressources/ressourceIng";
    }

    @Override
    public String ressourceBatimentMadiba(Model model, int page, int size) {
        String  name = "Madiba" ;
        Page<Ressources> ressources = ressourceService.getAllRessourcesByEcole(PageRequest.of(page -1,size),name);

        model.addAttribute("ressources", ressources);

        // la page actuelle
        model.addAttribute("currentPage", page);

        model.addAttribute("ecoleName", name);

        //le nombre total de page
        model.addAttribute("pages",ressources.getTotalPages());


        // Créer une liste de pages à afficher (avec des points de suspension si nécessaire)
        List<Integer> paginationPages = new ArrayList<>();
        int totalPages = ressources.getTotalPages();

        // Pages à afficher
        if (totalPages <= 4) {
            // Si le total des pages est <= 4, afficher toutes les pages
            for (int i = 1; i <= totalPages; i++) {
                paginationPages.add(i);
            }
        } else {
            // Afficher la première page, la dernière page et les pages autour de la page courante
            paginationPages.add(1); // première page

            // Afficher 2 pages avant et après la page courante, si elles existent
            if (page > 2) {
                paginationPages.add(page - 1);
            }
            paginationPages.add(page);
            if (page < totalPages - 1) {
                paginationPages.add(page + 1);
            }

            paginationPages.add(totalPages); // dernière page
        }
        model.addAttribute("paginationPages", paginationPages);



        return "public/ressources/ressourceIng";
    }

    @Override
    public String ressourceBatimentManagement(Model model, int page, int size) {
        String  name = "Management" ;
        Page<Ressources> ressources = ressourceService.getAllRessourcesByEcole(PageRequest.of(page -1,size),name);

        model.addAttribute("ressources", ressources);

        // la page actuelle
        model.addAttribute("currentPage", page);

        model.addAttribute("ecoleName", name);

        //le nombre total de page
        model.addAttribute("pages",ressources.getTotalPages());


        // Créer une liste de pages à afficher (avec des points de suspension si nécessaire)
        List<Integer> paginationPages = new ArrayList<>();
        int totalPages = ressources.getTotalPages();

        // Pages à afficher
        if (totalPages <= 4) {
            // Si le total des pages est <= 4, afficher toutes les pages
            for (int i = 1; i <= totalPages; i++) {
                paginationPages.add(i);
            }
        } else {
            // Afficher la première page, la dernière page et les pages autour de la page courante
            paginationPages.add(1); // première page

            // Afficher 2 pages avant et après la page courante, si elles existent
            if (page > 2) {
                paginationPages.add(page - 1);
            }
            paginationPages.add(page);
            if (page < totalPages - 1) {
                paginationPages.add(page + 1);
            }

            paginationPages.add(totalPages); // dernière page
        }
        model.addAttribute("paginationPages", paginationPages);



        return "public/ressources/ressourceIng";
    }


}
