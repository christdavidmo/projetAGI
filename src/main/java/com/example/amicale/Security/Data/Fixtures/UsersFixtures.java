package com.example.amicale.Security.Data.Fixtures;

import com.example.amicale.Data.Repository.UsersRepository;
import com.example.amicale.Security.Services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class UsersFixtures implements CommandLineRunner {

    private final SecurityService securityService;

    @Override
    public void run(String... args) throws Exception {

        securityService.saveUser("messi","passer1234");
        securityService.saveUser("Neymar","passer1234");
    }
}
