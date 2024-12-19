package com.example.amicale.Security.Controllers.Impl;




import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Security.Controllers.Security;
import com.example.amicale.Security.Services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
public class SecurityImpl implements Security {

    private final SecurityService securityService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(UserDetails user) {

        System.out.println(user);

        if(user!=null){
            if(user.getAuthorities().stream().anyMatch( c->c.getAuthority().compareTo("MemberCommunity")==0)){
                return "redirect:inscription";
            }
            if(user.getAuthorities().stream().anyMatch( c->c.getAuthority().compareTo("OfficeMember")==0)){
                Users appUser = securityService.getUserByUsername(user.getUsername());
                return "redirect:/office/accueil";
            }
        }
        return "login";
    }

    @Override
    public String HandleLogin(String login, String password, Model model) {

        // Affiche les valeurs reçues pour le débogage
        System.out.println("Login reçu: " + login);
        System.out.println("Password reçu: " + password);

        try {
            // Crée un token d'authentification
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);

            // Authentifie l'utilisateur
            Authentication authentication = authenticationManager.authenticate(token);

            // Mets à jour le SecurityContext avec l'authentification réussie
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/";  // Ou laisse Spring gérer la redirection automatique

        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Invalid username or password.");
            return "redirect:/login?error";  // Si les identifiants sont incorrects

        } catch (AuthenticationException e) {
            model.addAttribute("error", "Erreur d'authentification");
            return "redirect:/login";  // Si une autre erreur d'authentification survient
        }

    }

}

/*"Responsable_Com", "Responsable_Pedagie",
        "Responsable_Sport","Responsable_Trésorie",
        "President","Vice-President", "Secretaire-Génerale",
        "MemberCommunity","OfficeMember"*/