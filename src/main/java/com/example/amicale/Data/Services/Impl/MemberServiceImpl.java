package com.example.amicale.Data.Services.Impl;

import com.example.amicale.Data.Entity.Member;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Data.Enumeration.Statut;
import com.example.amicale.Data.Repository.MemberRepository;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Repository.UsersRepository;
import com.example.amicale.Data.Services.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final UsersRepository usersRepository;


    @Override
    public List<Member> getMembersByRole(Role role) {

        Role role1 = roleRepository.findByRolename(role);

        if( role1==null ){
            return null;
        }

        List<Member> memberRole= memberRepository.findMemberByRole(role1);
        return memberRole;
    }

    @Override
    public void blockedMember(Long id) {
        Member member = memberRepository.findMemberById(id) ;
        if(member!= null){
           member.setStatut(Statut.BLOQUE);
           memberRepository.save(member);
        } else {
            throw new EntityNotFoundException("Membre non trouvé");
        }
    }

    @Override
    public void unblockedMember(Long id) {
        Member member = memberRepository.findMemberById(id) ;

        if(member!= null){
            member.setStatut(Statut.ACTIF);
            memberRepository.save(member);
        }else {
            throw new EntityNotFoundException("Membre non trouvé");
        }

    }

    @Override
    public void deleteMember(Long id) {
        // 1. Vérifier si le membre existe
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé"));

        // 2. Dissocier l'utilisateur du rôle
        Users user = member instanceof Users ? (Users) member : null;
        if (user != null && user.getRole() != null) {
            // Dissocier le rôle de l'utilisateur
            user.setRole(null);  // Dissociation du rôle (le rôle reste dans la base)
            usersRepository.save(user);  // Sauvegarder l'utilisateur avec le rôle dissocié
        }

        // 3. Supprimer le membre
        memberRepository.delete(member);  // Supprimer le membre (si nécessaire)

        // 4. Optionnel : Supprimer l'utilisateur associé si c'est un `Member` (et pas un autre utilisateur)
        if (user != null) {
            usersRepository.delete(user);  // Supprimer l'utilisateur si nécessaire
        }
    }

    @Override
    public void toogleMemberStatus(Long id) {
        Member member = memberRepository.findMemberById(id) ;

        if (member != null) {

            if( member.getStatut() == Statut.ACTIF){
                member.setStatut(Statut.BLOQUE);
            }else{
                member.setStatut(Statut.ACTIF);
            }

            memberRepository.save(member);
        }
    }


    @Override
    public Member saveMember(Member member) {

        //chercher le membre via le nom
        Member member1 = memberRepository.findMemberByNom(member.getNom());


       if (member1 != null) {
           return member1;
       }
        return memberRepository.save(member);
    }




    @Override
    public List<Member> getAllMembersByActiveTrue() {
        // retourne tout ceux qui sont active true
        return memberRepository.findAllByActiveTrue();
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Page<Member> getAllMembersWithPageable(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public Page<Member> getMemberFilter(String Nom, Pageable pageable) {

        System.out.println("Filtrage par Nom: " + Nom); // Ajout de journalisation i
         if (Nom != null && !Nom.isEmpty()) {
             return memberRepository.findByNomContaining(Nom, pageable);
         }
         return memberRepository.findAll(pageable);

    }
}
