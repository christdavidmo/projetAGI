package com.example.amicale.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {
    
    @Column(nullable = false, unique = true)
    private String rolename;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "role")
    List<Users> users;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Member> members;  // Liste des membres ayant ce rôle


    public Role(String rolename) {
        this.rolename = rolename;
        this.active = true;
    }
}
