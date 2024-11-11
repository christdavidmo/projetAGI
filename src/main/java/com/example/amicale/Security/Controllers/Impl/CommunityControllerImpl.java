package com.example.amicale.Security.Controllers.Impl;

import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Security.Controllers.CommunityController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class CommunityControllerImpl implements CommunityController {

    @Override
    public String publicView() {
       return "homepage";
        // return "layout/navbar/navbar";
    }

    @Override
    public String aboutusView() {
        return "aboutus";
        // return "layout/navbar/navbar";
    }

    @Override
    public String activitesView() {
        return "activite";
        // return "layout/navbar/navbar";
    }


    @Override
    public String communityView() {
        return "communityHome";
    }


}
