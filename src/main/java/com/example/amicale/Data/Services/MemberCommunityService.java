package com.example.amicale.Data.Services;

import com.example.amicale.Data.Entity.MemberCommunity;

public interface MemberCommunityService {

    MemberCommunity getMemberCommunityById(Long id);
    MemberCommunity saveMemberCommunity(MemberCommunity mc);
}
