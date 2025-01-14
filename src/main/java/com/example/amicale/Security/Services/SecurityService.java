package com.example.amicale.Security.Services;

import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Entity.Users;

import java.util.List;


public interface SecurityService {

    Users getUserByUsername(String username);
    Users saveUser(String username,String password);

    Role saveRole(Role  rolename);
    Role getRoleByName(String roleName);
    Role getRoleById(Long  id);

    List<Role> getAllRoles();

    void EditRoleToUser(String username,String roleName);
}
