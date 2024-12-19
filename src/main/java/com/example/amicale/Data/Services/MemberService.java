package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {


    public void blockedMember(Long id);
    public void unblockedMember(Long id);
    public void deleteMember(Long id);
    public void toogleMemberStatus(Long id);


    Member saveMember(Member member);

    List<Member> getAllMembersByActiveTrue();
    List<Member> getAllMembers();
    List<Member> getMembersByRole(Role role);


    Page<Member> getAllMembersWithPageable(Pageable pageable);
    Page<Member> getMemberFilter(String Nom ,Pageable pageable);
}
