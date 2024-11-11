package com.example.amicale.Security.Controllers.Impl;

import com.example.amicale.Security.Controllers.officeController;
import org.springframework.stereotype.Controller;



@Controller
public class officeControllerImpl implements officeController {

    @Override
    public String accueil() {
        return "office/HomeOffice";
    }
}
