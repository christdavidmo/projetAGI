package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Mandat;
import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.MemberMandatRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberMandatRoleRepository extends JpaRepository<MemberMandatRole, Long> {

    // Requête pour récupérer les roles d'un membre pour un mandat spécifique
    List<MemberMandatRole> findByMandat(Mandat mandat);

    // Requête pour récupérer un rôle spécifique d'un membre dans un mandat
    Optional<MemberMandatRole> findByMandatAndMember(Mandat mandat, Member member);
    MemberMandatRole findByMemberAndMandat( Member member,Mandat mandat);
}
