package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.MemberCommunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCommunityRepository extends JpaRepository<MemberCommunity, Long> {
    MemberCommunity findById(long id);
}
