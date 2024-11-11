package com.example.amicale.Security.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

public interface officeController {

    @GetMapping("/office/accueil")
    public String accueil();
}
