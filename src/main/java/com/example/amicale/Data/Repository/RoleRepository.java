package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByRolename(String username);

    boolean existsByRolename(String roleName);

    Role findByRolename(String name);
}
