package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.OfficeMember;
import com.example.amicale.Data.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficeMemberRepository extends JpaRepository<OfficeMember, Long> {

    OfficeMember findById(long id);
    OfficeMember findByLogin(String name);

    List<OfficeMember> findAll();

    List <OfficeMember>  findByRole(Role role);
}
