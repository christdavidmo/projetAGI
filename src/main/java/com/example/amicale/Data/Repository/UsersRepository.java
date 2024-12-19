package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findByRole_Rolename(String rolename);
    Users findByLogin(String username);
    Boolean existsByLogin(String username);

}
