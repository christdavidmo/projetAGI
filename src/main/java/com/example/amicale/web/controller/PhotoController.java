package com.example.amicale.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoController {


    @PostMapping("/image/add/{idEvenement}")
    public String addPhotoToEvenement(@PathVariable("idEvenement") Long idEvenement,
                                      @RequestParam MultipartFile file,
                                      Model model) throws IOException;
}
