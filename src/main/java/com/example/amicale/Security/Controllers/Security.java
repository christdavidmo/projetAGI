package com.example.amicale.Security.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

public interface Security {

    @GetMapping("/login")
    String login(@AuthenticationPrincipal UserDetails user);


    @PostMapping("/login")
    String HandleLogin(@RequestParam String login, @RequestParam String password, Model model);

}
