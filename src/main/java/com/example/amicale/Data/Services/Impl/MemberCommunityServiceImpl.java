package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.MemberCommunity;
import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Data.Repository.MemberCommunityRepository;
import com.example.amicale.Data.Services.MemberCommunityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommunityServiceImpl implements MemberCommunityService {

    private final MemberCommunityRepository memberCommunityRepository;


    @Override
    public MemberCommunity getMemberCommunityById(Long id) {
        return memberCommunityRepository.findById(id).orElse(null);
    }


    @Override
    public MemberCommunity saveMemberCommunity(MemberCommunity mc) {
        MemberCommunity mcn = new MemberCommunity();
        return memberCommunityRepository.save(mc);

        /*Users users = new Users();
        users.setLogin(username);
        users.setPassword(passwordEncoder.encode( password));
        return usersRepository.save(users);*/
    }
}
