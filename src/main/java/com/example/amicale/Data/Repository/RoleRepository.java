package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByRolename(String username);

    boolean existsByRolename(String roleName);

    Role findByRolename(Role rolename);

    Role findByRolename(String rolename);

    Role findRoleById(long id);


}
