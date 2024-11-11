package com.example.amicale.Security.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface CommunityController {

    @GetMapping("/")
      public String publicView();

    @GetMapping("/public/aboutus")
    public String aboutusView();

    @GetMapping("/public/activites")
    public String activitesView();

    @GetMapping("/community")
    public String communityView();






}
