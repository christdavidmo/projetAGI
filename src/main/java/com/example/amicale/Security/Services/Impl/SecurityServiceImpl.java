package com.example.amicale.Security.Services.Impl;

import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Data.Repository.RoleRepository;
import com.example.amicale.Data.Repository.UsersRepository;
import com.example.amicale.Security.Services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService, UserDetailsService {


    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users getUserByUsername(String username) {
        return usersRepository.findByLogin(username);
    }

    @Override
    public Users saveUser(String username, String password) {
        Users user = usersRepository.findByLogin(username);

        if(user != null) {
            throw new SecurityException("Username already exists");
        }

        Users users = new Users();
        users.setLogin(username);
        users.setPassword(passwordEncoder.encode( password));
        return usersRepository.save(users);

    }




    @Override
    public Role saveRole(Role rolename) {
        Role role = roleRepository.findByRolename(rolename);
        if(role != null) {
            throw new SecurityException("ce role existe déja");
        }
        return roleRepository.save(role);
    }


    @Override
    public Role getRoleByName(String roleName) {

        return roleRepository.getByRolename(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void EditRoleToUser(String username, String roleName) {

        Users user = usersRepository.findByLogin(username);
        Role role = roleRepository.getByRolename(roleName);


        if (user == null) {
            throw new SecurityException("Ce User n'existe pas");
        }
        if (role == null) {
            throw new SecurityException("Ce Role n'existe pas");
        }

        user.setRole(role);
        usersRepository.save(user);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersRepository.findByLogin(username);
        System.out.println(user);
        if( user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRolename());
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(authority);

        return new User(user.getLogin(), user.getPassword(), authorities);

        /*return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRoles().iterator().next().getRolename())));*/
    }
}

