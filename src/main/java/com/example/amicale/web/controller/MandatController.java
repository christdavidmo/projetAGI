package com.example.amicale.web.controller;

import com.example.amicale.Data.Entity.Mandat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface MandatController {


    @GetMapping("/mandat/form")
    public String FormulaireMandat(Model model);

    @PostMapping("/mandat/save")
    public String enregistrementMandat(@RequestParam Map<String, String> formData);
}
