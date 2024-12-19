package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findMemberById(long id);
    List<Member> findMemberByRole(Role role);
    List<Member> findAllByActiveTrue();
    List<Member> findAll();


    Page<Member> findAll(Pageable pageable);
    Page<Member> findByNomContaining(String nom, Pageable pageable);


    Member findMemberByNom(String nom);
    Member findMemberById(Long id);


}
