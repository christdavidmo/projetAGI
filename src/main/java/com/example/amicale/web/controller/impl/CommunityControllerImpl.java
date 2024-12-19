package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Evenement;
import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Services.EvenementService;
import com.example.amicale.Data.Services.MandatService;
import com.example.amicale.web.controller.CommunityController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommunityControllerImpl implements CommunityController {


    @Override
    public String home(Model model) {
        //return "layout/navbar/navbarCommunity";
        return "community/home";
    }
}
