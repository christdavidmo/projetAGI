package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Services.MemberService;
import com.example.amicale.web.controller.officeController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class officeControllerImpl implements officeController {


    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String accueiloffice() {
        //return "layout/navbar/navbarOffice";
        return "office/homeoffice";
    }

    @Override
    public String gestionoffice() {
        return "office/gestion";
    }



    @Override
    public String Presidentaccueil() {
        return "president/homePresident";
       //return "layout/navbar/navbarPresident" DetailgestionPresident;
    }

    @Override
    public String Presidentgestionoffice(Model model) {
        return "president/gestionPresident";
    }




    @Override
    public String Presidentmodifiermember(Model model,
                                          Long id,
                                          String nom,
                                          String prenoms,
                                          String sexe,
                                          String email,
                                          String matricule) {

        Member member = memberRepository.findMemberById(id);
        if (member != null) {
            member.setNom(nom);
            member.setPrenoms(prenoms);
            member.setSexe(sexe);
            member.setEmail(email);
            member.setMatricule(matricule);
            memberRepository.save(member);
        }
        return "redirect:/president/gestion/detail";
    }

    @Override
    public String Presidentgestiondetail(Model model
            ,@RequestParam(defaultValue = "1") int page
            ,@RequestParam(defaultValue = "8") int size
            ,@RequestParam(required = false) String Nom) {

        Page<Member> members = memberService.getMemberFilter(Nom, PageRequest.of(page -1,size));

        model.addAttribute("members", members.getContent());
        model.addAttribute("pages", members.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("Nom", Nom);

        return "president/detailgestionPresident";
    }

    @Override
    public String PresidentgestiondetailSave(Model model
                                            ,String nom
                                            , String prenoms
                                            , String sexe
                                            , String email
                                            , String matricule) {

        Member memberExisting = memberRepository.findMemberByNom(nom);

        if (memberExisting != null) {
            model.addAttribute("error, ce nom d'utilisateur existe déja");
            return "redirect:/president/gestion/detail";
        }

            Member member = new Member();

            member.setNom(nom);
            member.setPrenoms(prenoms);
            member.setSexe(sexe);
            member.setEmail(email);
            member.setMatricule(matricule);

            member.setLogin(member.getMatricule());
            member.setPassword(passwordEncoder.encode("passer"));
            member.setActive(true);
            memberRepository.save(member);


        return "redirect:/president/gestion/detail";
    }

    @Override
    public String toogleMemberStatus(Long id) {
        memberService.toogleMemberStatus(id);
        return "redirect:/president/gestion/detail";
    }


    @Override
    public ResponseEntity<?> Presidentdelete(Model model, Long id) {
       try{
           memberService.deleteMember(id);
           return ResponseEntity.ok().build();

       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du membre");
       }
    }


    @Override
    public String Presidentparametreoffice() {
        return "public/test";
    }
}
