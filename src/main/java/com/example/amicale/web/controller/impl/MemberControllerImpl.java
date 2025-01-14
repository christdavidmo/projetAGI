package com.example.amicale.web.controller.impl;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Services.MandatService;
import com.example.amicale.Data.Services.MemberMandatRoleServices;
import com.example.amicale.Data.Services.MemberService;
import com.example.amicale.Security.Services.SecurityService;
import com.example.amicale.web.controller.MemberController;
import com.example.amicale.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberControllerImpl implements MemberController {

    private final MemberRepository memberRepository;
    @Autowired
    private final MemberService memberService;

    private final MandatService mandatService;

    private final SecurityService securityService;

    private final MemberMandatRoleServices memberMandatRoleServices;

    private final RoleRepository roleRepository;

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

    @Override
    public String changeRoleMember(Model model,Long idMandat, Long idMember, Long idRole) {

        // Log pour vérifier les paramètres reçus
        System.out.println("les paremetre recus : idMandat: " + idMandat + ", idMember: " + idMember + ", idRole: " + idRole);

        Member member = memberService.getMemberById(idMember);
        Mandat mandat = mandatService.getMandatById(idMandat);
        Role newRole = securityService.getRoleById(idRole);

        // Log pour vérifier si les objets sont correctement récupérés
        System.out.println("les objet recupérés ; Member: " + member.getNom() + ", Role: " + newRole.getRolename());

        List<Role> roles = roleRepository.findAll();
        List<Member> members = mandatService.GetMandatById(idMandat);


        model.addAttribute("AllRoles",roles);
        model.addAttribute("mandat", mandat);
        model.addAttribute("members", members);


        memberMandatRoleServices.ChangeROleInMandat(mandat,member,newRole);



        return "redirect:/president/mandat/" + idMandat + "/modification";
    }

    @Override
    public String DeleteMemberInMandat(Model model, Long idMandat, Long idMember) {

        memberMandatRoleServices.deleteMemberRoleInMandat(idMandat,idMember);
        return "redirect:/president/mandat";
    }

    @Override
    public String SaveMemberInMandat(Long idMandat, Long idmember, Long idRole) {
        memberMandatRoleServices.AddMemberRoleToMandat(idMandat,idmember,idRole);
        return "redirect:/president/mandat";
    }


    //==================== DTO ===========================

    @Override
    public ResponseEntity<?> getAllMembersDto() {
        try{
            List<MemberDto> members = memberService.getAllMemberDto();
            return ResponseEntity.ok().body(members);

        } catch (Exception e) {
            e.printStackTrace(); // Affiche la trace d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne du serveur : " + e.getMessage());
        }

    }

    @Override
    public MemberDto getMemberDto(Long id, Model model) {

        Member member = memberService.getMemberById(id);
        if (member == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found");
        }
        return new MemberDto(member.getId(), member.getNom(), member.getPrenoms());
    }


}
