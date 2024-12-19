package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.MandatRepository;
import com.example.amicale.Data.Services.MandatService;
import com.example.amicale.Data.Services.MemberService;
import com.example.amicale.Security.Services.SecurityService;
import com.example.amicale.web.controller.MandatController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MandatControllerImpl implements MandatController {

    private final MemberService memberService ;
    private final MandatService mandatService ;
    private final MandatRepository mandatRepository ;
    private final SecurityService securityService ;

    @Override
    public String  FormulaireMandat(Model model) {

        //recupere d'abord tous les membres
        List<Member> members = memberService.getAllMembersByActiveTrue();

        //Tous les roles
        List<Role> roles = securityService.getAllRoles();

        //liste filtrée
        List<Role> rolesFilters = new ArrayList<Role>();

        for (Role role : roles) {
            if(     !role.getRolename().equals("MemberCommunity") &&
                    !role.getRolename().equals("OfficeMember") &&
                    !role.getRolename().equals("President") ){
                rolesFilters.add(role);
            }
        }

        //créer un mandat
        Mandat mandat = new Mandat();

        model.addAttribute("mandat",mandat);
        model.addAttribute("roles",rolesFilters);
        model.addAttribute("members", members);

        return "mandat/ajouterMandat";
    }

    @Override
    public String enregistrementMandat(Model model, Mandat mandat) {
        mandat.setActive(true);
        mandatRepository.save(mandat);
        return "redirect:/mandat/ajouterMandat";
    }
}
