package com.example.amicale.web.controller;

import com.example.amicale.web.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberController {

    @GetMapping("/member/{id}/edit")
    public  String EditMember(Model model, @PathVariable Long id);

    @GetMapping("/member/all")
    @ResponseBody
    public ResponseEntity<?> getAllMembersDto();

    @GetMapping("/member/{id}")
    @ResponseBody
    public MemberDto  getMemberDto(@PathVariable("id") Long id,Model model);

    @PostMapping("/member/{id}/uploadPhoto")
    public String  uploadPhoto(@PathVariable Long id,
                               @RequestParam("photo") MultipartFile photo);

    @PostMapping("/member/{idMandat}/changeRole")
    public String changeRoleMember(Model model,
                                   @PathVariable Long idMandat,
                                    @RequestParam Long idMember,
                                    @RequestParam Long idRole);

    @PostMapping("/member/{idMandat}/DeleteMemberInMandat")
    public String DeleteMemberInMandat(Model model,
                                   @PathVariable Long idMandat,
                                   @RequestParam Long idMember );

    @PostMapping("/member/{idMandat}/SaveMemberInMandat")
    public String SaveMemberInMandat(@PathVariable Long idMandat,
                                     @RequestParam Long idmember,
                                     @RequestParam Long idRole);
}
