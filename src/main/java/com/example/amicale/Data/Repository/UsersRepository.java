package com.example.amicale.Data.Repository;

import com.example.amicale.Data.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {


    Users findByLogin(String username);
    Boolean existsByLogin(String username);
}
