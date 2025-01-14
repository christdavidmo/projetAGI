package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.MandatRepository;
import com.example.amicale.Data.Repository.MemberMandatRoleRepository;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Services.MandatService;
import com.example.amicale.Data.Services.MemberMandatRoleServices;
import com.example.amicale.Security.Services.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class MemberMandatRoleServicesImpl implements MemberMandatRoleServices {

    private final MemberMandatRoleRepository memberMandatRoleRepository;
    private final MandatRepository mandatRepository;
    private final MemberRepository memberRepository;
    private final SecurityService securityService;
    private final RoleRepository roleRepository;

    @Override
    public void saveMemberMandatRoles(List<MemberMandatRole> memberMandatRoles) {
        memberMandatRoleRepository.saveAll(memberMandatRoles);
    }

    @Override
    public void ChangeROleInMandat(Mandat mandat, Member member, Role newRole) {

        // Log pour vérifier si le rôle du membre est bien trouvé
        System.out.println("Attempting to change role for member " + member.getNom() + " in mandat " + mandat.getId());

        Optional<MemberMandatRole> existingRoleOptional = memberMandatRoleRepository.findByMandatAndMember(mandat, member);
        if (existingRoleOptional.isPresent()) {
            MemberMandatRole existingRole = existingRoleOptional.get();
            System.out.println("Found existing role: " + existingRole.getRole().getRolename());

            existingRole.setRole(newRole);
            memberMandatRoleRepository.save(existingRole);

            System.out.println("Role updated to: " + newRole.getRolename());
        } else {
            System.out.println("No existing role found for this member.");
        }

    }

    @Override
    public List<MemberMandatRole> getMembersWithRolesByMandat(Long mandatId) {
        // Vérifier que le mandat existe
        Mandat mandat = mandatRepository.findById(mandatId)
                .orElseThrow(() -> new IllegalArgumentException("Mandat non trouvé"));

        // Retourner la liste des roles des membres pour ce mandat
        return memberMandatRoleRepository.findByMandat(mandat);
    }

    @Override
    public MemberMandatRole getMemberRoleInMandat(Long mandatId, Long memberId) {
        // cherche d'abord si le mandat existe
        Mandat mandat = mandatRepository.findById(mandatId)
                .orElseThrow(() -> new IllegalArgumentException("Mandat non trouvé"));

        // cherhche si le Membre existe
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));

        //cherche si le membre existe dans le mandat
        return memberMandatRoleRepository.findByMandatAndMember(mandat, member)
                .orElseThrow(() -> new IllegalArgumentException("Le membre n'a pas de rôle dans ce mandat"));
    }

    @Override
    public List<MemberMandatRole> getMembersByMandat(Mandat mandat) {
        return memberMandatRoleRepository.findByMandat(mandat);
    }

    @Override
    public void deleteMemberRoleInMandat(Long mandatId, Long memberId) {

        //cherche d'abord si il existe
        Mandat mandat = mandatRepository.findById(mandatId)
                .orElseThrow(() -> new IllegalArgumentException("Mandat non trouvé"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Membre non teouvé"));;

        if (mandat != null || member != null) {
            MemberMandatRole mandatRole = memberMandatRoleRepository.findByMemberAndMandat(member, mandat);
            memberMandatRoleRepository.delete(mandatRole);
        }





    }

    @Override
    public void AddMemberRoleToMandat(Long idMandat, Long idmember, Long idRole) {


        MemberMandatRole mandatRole = new MemberMandatRole();

        //verifie si il existe
        Member member = memberRepository.findById(idmember)
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));

        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new IllegalArgumentException("Role non trouvé"));

        Mandat mandat = mandatRepository.findById(idMandat)
                .orElseThrow(() -> new IllegalArgumentException("Role non trouvé"));

        if(member != null || role != null || mandat != null) {
            mandatRole.setMandat(mandat);
            mandatRole.setRole(role);
            mandatRole.setMember(member);

            memberMandatRoleRepository.save(mandatRole);
        }

    }


}
