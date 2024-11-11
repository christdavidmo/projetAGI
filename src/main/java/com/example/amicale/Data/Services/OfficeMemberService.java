package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.MemberCommunity;
import com.example.amicale.Data.Entity.OfficeMember;
import com.example.amicale.Data.Entity.Role;

import java.util.List;

public interface OfficeMemberService {

    OfficeMember getOfficeMemberById(Long id);
    OfficeMember saveOfficeMember(OfficeMember OM);
    List<OfficeMember> getOfficeMemberByRole(Role role);
}
