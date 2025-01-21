package com.example.amicale.Security.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login") // URL où le formulaire POST sera traité
                        .failureUrl("/login?error") // Redirection après un échec d'authentification
                        .permitAll()
                        .successHandler((request, response, authentication) -> {

                            Set<String> rolesToRedirectOffice = Set.of(
                                    "Responsable_Communication", "Responsable_Pedagogie",
                                    "Responsable_Sport", "Responsable_Tresorie"
                                    , "Vice_President", "Secretaire_Generale","OfficeMember"
                            );

                            // Récupérer les autorités de l'utilisateur connecté
                            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                            if (auth.getAuthorities().stream().anyMatch(a ->  rolesToRedirectOffice.contains(a.getAuthority()))) {
                                response.sendRedirect("/office/accueil");  // Redirige vers la page des membres du bureau

                            }else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("President"))) {
                                response.sendRedirect("/president/accueil");  // Redirige vers la page

                            }else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("MemberCommunity"))) {
                                response.sendRedirect("/community/home");  // Redirige vers la page de la communauté

                            } else {
                                response.sendRedirect("/public/home");  // Redirection par défaut
                            }
                        })
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public/**", "/connexion","/mandat/**").permitAll()
                        .requestMatchers("/css/**", "/images/**", "/js/**", "/webjars/**").permitAll()
                        .requestMatchers("/office/**","/member/**","/president/**").hasAuthority("President")
                        .requestMatchers("/community/**").hasAuthority("MemberCommunity")
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
