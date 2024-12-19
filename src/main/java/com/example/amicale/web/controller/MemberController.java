package com.example.amicale.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface MemberController {

    @GetMapping("/member/{id}/edit")
    public  String EditMember(Model model, @PathVariable Long id);

    @PostMapping("/member/{id}/uploadPhoto")
    public String  uploadPhoto(@PathVariable Long id,@RequestParam("photo") MultipartFile photo);
}
