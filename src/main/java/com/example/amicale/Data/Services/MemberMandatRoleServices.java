package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import com.example.amicale.Data.Entity.Role;

import java.util.List;

public interface MemberMandatRoleServices {

    public void saveMemberMandatRoles(List<MemberMandatRole> memberMandatRoles);
    public void ChangeROleInMandat(Mandat mandat, Member member , Role newRole);
    public void deleteMemberRoleInMandat(Long mandatId, Long memberId);
    public void AddMemberRoleToMandat(Long idMandat,Long idmember, Long idRole);

    public List<MemberMandatRole> getMembersWithRolesByMandat(Long mandatId);

    // Récupérer un membre spécifique dans un mandat
    public MemberMandatRole getMemberRoleInMandat(Long mandatId, Long memberId);

    List<MemberMandatRole> getMembersByMandat(Mandat mandat);


}
