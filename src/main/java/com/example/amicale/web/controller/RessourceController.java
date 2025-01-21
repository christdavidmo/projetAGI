package com.example.amicale.web.controller;

import com.example.amicale.Data.Enumeration.TypeRessource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface RessourceController {

    @GetMapping("/ressource")
    public String ressources(Model model,
                             @RequestParam(defaultValue = "1")int page,
                             @RequestParam(defaultValue = "6")int size ,
                             @RequestParam(required = false)  String titleRessource);

    @GetMapping("/ressource/Add")
    public String ressourceForm(Model model);

    //MultipartFile file, TypeRessource type,Long ecoleId
    @PostMapping("/ressource/Add")
    public String ressourceSave(Model model,
                                @RequestParam("file") MultipartFile file ,
                                @RequestParam TypeRessource typeRessource ,
                                @RequestParam Long ecoleId);




}
