package com.example.amicale.Data.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberMandatRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;  // Membre qui a un rôle dans ce mandat

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mandat_id", nullable = false)
    private Mandat mandat;  // Mandat auquel ce membre est associé

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;  // Rôle spécifique du membre dans ce mandat

    // Constructor pour utiliser une chaîne de caractères
    public MemberMandatRole( Member member,Mandat mandat, Role roleName) {
        this.mandat = mandat;
        this.member = member;
        this.role = roleName;  // Créer un objet Role à partir du nom
    }

}
