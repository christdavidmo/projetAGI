package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Ressources;
import com.example.amicale.Data.Enumeration.TypeRessource;
import com.example.amicale.Data.Services.RessourceServices;
import com.example.amicale.web.controller.RessourceController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@AllArgsConstructor

public class RessourceControllerImpl implements RessourceController {

    private final RessourceServices ressourceService;


    @Override
    public String ressources(Model model) {

        List<Ressources> ressources = ressourceService.getAllRessources();
        model.addAttribute("ressources", ressources);
        return "president/ressource/ressource";
    }

    @Override
    public String ressourceAdd(Model model) {

        model.addAttribute("TypeRessources", TypeRessource.values());
        return "president/ressource/AjoutRessource";
    }


}
