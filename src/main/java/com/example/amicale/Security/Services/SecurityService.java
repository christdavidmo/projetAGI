package com.example.amicale.Security.Services;

import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Entity.Users;



public interface SecurityService {

    Users getUserByUsername(String username);
    Users saveUser(String username,String password);

    Role saveRole(Role role);
    Role getRoleByName(String roleName);

    void EditRoleToUser(String username,String roleName);
}
