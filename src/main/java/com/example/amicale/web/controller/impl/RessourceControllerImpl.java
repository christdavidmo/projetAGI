package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Ecole;
import com.example.amicale.Data.Entity.Ressources;
import com.example.amicale.Data.Enumeration.TypeRessource;
import com.example.amicale.Data.Services.EcoleServices;
import com.example.amicale.Data.Services.RessourceServices;
import com.example.amicale.web.controller.RessourceController;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor

public class RessourceControllerImpl implements RessourceController {

    private final RessourceServices ressourceService;
    private final EcoleServices ecoleServices;


    @Override
    public String ressources(Model model,
                             @RequestParam(defaultValue = "1")int page ,
                             @RequestParam(defaultValue = "6")int size,
                             @RequestParam(required = false) String titleRessource) {

        Page<Ressources> ressources = ressourceService.getAllRessourcesPage(titleRessource,PageRequest.of(page-1, size));
        model.addAttribute("ressources", ressources);
        return "president/ressource/ressource";
    }



    @Override
    public String ressourceForm(Model model) {

        List<Ecole> ecoles = ecoleServices.getEcoles();

        model.addAttribute("ecoles", ecoles);
        model.addAttribute("TypeRessources", TypeRessource.values());
        return "president/ressource/AjoutRessource";
    }

    @Override
    public String ressourceSave(Model model,
                                MultipartFile file,
                                TypeRessource typeRessource,
                                Long ecoleId) {

        try {
            ressourceService.saveRessources(file,typeRessource,ecoleId);
            model.addAttribute("message","ressource ajoutée avec succès");

        } catch (IOException e) {
            model.addAttribute("error", "Erreur lors de l'ajout de la ressource : " + e.getMessage());
        }
        return "president/ressource/AjoutRessource";
    }




}
