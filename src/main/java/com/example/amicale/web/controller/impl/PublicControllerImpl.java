package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.Photo;
import com.example.amicale.Data.Services.EvenementService;
import com.example.amicale.Data.Services.MandatService;
import com.example.amicale.Data.Services.PhotoServices;
import com.example.amicale.web.controller.PublicController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicControllerImpl implements PublicController {

    private final EvenementService evenementService;
    private final MandatService mandatService;
    private final PhotoServices photoServices;


    @Override
    public String publicView() {
        return "public/homepage";
        // return "layout/navbar/navbar";
    }

    @Override
    public String aboutusView() {
        return "public/aboutus";
        // return "layout/navbar/navbar";
    }

    @Override
    public String activitesView(Model model
                                ,@RequestParam(defaultValue = "1") int page
                                ,@RequestParam(defaultValue = "6") int size
                                ,@RequestParam(required = false) String name) {


        //recupère d'abord  tous les elements sosus forme d'une liste
        Page<Evenement> evenements = evenementService.getFilterEvenement(name , PageRequest.of(page -1,size));
        model.addAttribute("evenements", evenements.getContent());

        //le nombre total de page
        model.addAttribute("pages", evenements.getTotalPages());

        //la page actuelle
        model.addAttribute("currentPage", page);

        model.addAttribute("name",name);

        return "public/activite";
    }

    @Override
    public String detailactivitesView(Model model,
                                      @PathVariable Long id) {

        List<Photo> AllImages = photoServices.getAllToEvenement(id);

        model.addAttribute("nbrImages", AllImages.size());
        model.addAttribute("photos", AllImages);

        return "public/detailsActivite";
    }


    @Override
    public String contactView() {
        return "public/contact";
    }

    @Override
    public String historiqueBureauView(Model model ,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "3") int size) {
        Page<Mandat> lesmandats = mandatService.GetAllMandat(PageRequest.of(page -1,size));

        model.addAttribute("lesmandats", lesmandats.getContent());
        model.addAttribute("pages", lesmandats.getTotalPages());

        model.addAttribute("currentPage", page);

        return "public/historique";
    }

    @Override
    public String historiqueDetailBureauView(Model model,@PathVariable Long id) {

        List<Member> members = mandatService.GetMandatById(id);
        model.addAttribute("members", members);

        return "public/detailsHistorique";
    }


    @Override
    public String testView() {
        return "public/test2";
    }

    @Override
    public String ErrorView() {
        return "error/error404";
    }
}
