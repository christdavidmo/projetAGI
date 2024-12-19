package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.web.controller.MemberController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController {

    private final MemberRepository memberRepository;
    private static String UPLOAD_DIR = "src/main/resources/static/uploads/";


    @Override
    public String EditMember(Model model, Long id) {
        //cherche d'abord si l'utilisateur existe
        Optional<Member> member = memberRepository.findById(id);

        if(member.isPresent()) {
            model.addAttribute("member", member.get());
            return "member/memberedit";

        }
        return "error/error404";
    }

    @Override
    public String uploadPhoto(Long id, MultipartFile photo) {

        //cherche d'abord si l'utilisateur existe
        Optional<Member> member = memberRepository.findById(id);


        //si l'utilisateur existe
        if(member.isPresent()) {

                try{

                    byte[] bytes = photo.getBytes();
                    Path path = Paths.get(UPLOAD_DIR + photo.getOriginalFilename());
                    Files.write(path, bytes);

                    Member memberEntity = member.get();
                    memberEntity.setPhotoUrl("/uploads/" + photo.getOriginalFilename());
                    memberRepository.save(memberEntity);

                    /*  memberEntity.setPhotoUrl(path.toString());
                    member/{id}/edi , /member/{id}/edit*/
                   return "redirect:/member/"+ id +"/edit";

                } catch (IOException e) {
                   e.printStackTrace();
                }

        }
        return "redirect:/member/" + id + "/edit?error=true";
    }
}
