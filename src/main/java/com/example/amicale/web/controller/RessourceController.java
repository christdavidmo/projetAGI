package com.example.amicale.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface RessourceController {

    @GetMapping("/ressource")
    public String ressources(Model model);

    @GetMapping("/ressource/Add")
    public String ressourceAdd(Model model);




}
