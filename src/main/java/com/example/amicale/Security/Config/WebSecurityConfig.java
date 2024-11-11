package com.example.amicale.Security.Config;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;




import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(requests -> requests
                        .requestMatchers("/", "/public/**", "/home", "/about", "/contact", "/connexion", "/inscription", "/enregistrement", "/login", "/favicon.ico").permitAll()
                        .requestMatchers("/css/**", "/images/**", "/js/**", "/webjars/**").permitAll()
                        .requestMatchers("/community").hasAuthority("MemberCommunity") // Utilise hasAuthority ici
                        .anyRequest().permitAll())
                .formLogin(login -> login
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/", true)

                        .successHandler((request, response, authentication) -> {
                            System.out.println("Authenticated: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
                            String role = authentication.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse("");


                                 }
                            )
                        )
                .logout(logout -> logout.permitAll());

        return http.build();
    }


        //crypter notre Mot de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }







}
