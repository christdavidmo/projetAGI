package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Services.EvenementService;
import com.example.amicale.Data.Services.PhotoServices;
import com.example.amicale.web.controller.PhotoController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class PhotoControllerImpl implements PhotoController {
    private final PhotoServices photoServices;
    private final EvenementService evenementService;


    @Override
    public String addPhotoToEvenement( Long idEvenement,
                                       MultipartFile file,
                                       Model model) throws IOException {

        try {
            Evenement evenement = evenementService.getEvenementById(idEvenement);
            photoServices.saveImage(file,evenement);

            model.addAttribute("message", "l'image a été téléchargée avec succès");

        } catch (Exception e) {
           model.addAttribute("message", "un problème est survenu au moment du téléchargement de l'image");
        }

        return "redirect:/president/contenu/evenement";
    }
}
