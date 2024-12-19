package com.example.amicale.web.controller;

import com.example.amicale.Data.Entity.Mandat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface MandatController {


    @GetMapping("/mandat/form")
    public String FormulaireMandat(Model model);

    @PostMapping("/mandat/save")
    public String enregistrementMandat(Model model, Mandat mandat);
}
