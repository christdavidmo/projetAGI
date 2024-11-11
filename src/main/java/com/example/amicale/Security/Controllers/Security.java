package com.example.amicale.Security.Controllers;

import com.example.amicale.Data.Entity.MemberCommunity;
import org.springframework.ui.Model;

import com.example.amicale.Data.Entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Security {

    @GetMapping("/inscription")
    public String inscription(Model model);

    @GetMapping("/login")
    public String login(Model model);


    @PostMapping("/enregistrement")
     public ResponseEntity<?>  enregistrement(@ModelAttribute MemberCommunity memberCommunity);

    @PostMapping("/connexion")
    public String  connexion(@ModelAttribute Users users, Model model);

}
