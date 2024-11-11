package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.MemberCommunity;
import com.example.amicale.Data.Entity.OfficeMember;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Repository.OfficeMemberRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Services.OfficeMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OfficeMemberServiceImpl implements OfficeMemberService {

    private final OfficeMemberRepository officeMemberRepository;



    @Override
    public OfficeMember getOfficeMemberById(Long id) {
        return officeMemberRepository.findById(id).orElse(null);
    }

    @Override
    public OfficeMember saveOfficeMember(OfficeMember OM) {
        return officeMemberRepository.save(OM);
    }

    @Override
    public List<OfficeMember> getOfficeMemberByRole(Role role) {
        return officeMemberRepository.findByRole(role);
    }
}
