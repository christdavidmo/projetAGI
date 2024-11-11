package com.example.amicale.Security.Controllers.Impl;




import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.stream.Collectors;



import com.example.amicale.Data.Entity.MemberCommunity;
import com.example.amicale.Data.Entity.Role;
import com.example.amicale.Data.Entity.Users;
import com.example.amicale.Data.Repository.UsersRepository;
import com.example.amicale.Data.Services.MemberCommunityService;
import com.example.amicale.Security.Controllers.Security;
import com.example.amicale.Security.Services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;


@Controller
@RequiredArgsConstructor
public class SecurityImpl implements Security {

    private final AuthenticationManager authenticationManager;
    private UsersRepository usersRepository;
    private final SecurityService securityService;
    private final MemberCommunityService memberCommunityService;
    private final PasswordEncoder passwordEncoder;




    @Override
    public String inscription(Model model) {
        model.addAttribute("user", new Users());
        return "inscription";
    }


    @Override
    public ResponseEntity<?> enregistrement( MemberCommunity memberCommunity) {

        // verie d 'abord si le user n'existe pas déja
        if (securityService.getUserByUsername(memberCommunity.getLogin()) != null) {
            return  ResponseEntity.badRequest().body("Ce nom d'utilisateur existe déja");
        }

        //gerer son role lors de l'enregistrement
        Role role = securityService.getRoleByName("MemberCommunity");

        if( role == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur au niveau du role");
        }
        memberCommunity.setRole(role);

        //Matricule
        memberCommunity.setMatricule("Agi"+memberCommunity.getNom()+"24");

        // Créer une date d'adhésion à partir de la date actuelle
        memberCommunity.setDateOfJoining(new Date(System.currentTimeMillis())); // Utiliser System.currentTimeMillis() pour obtenir la date actuelle


        String PW = passwordEncoder.encode(memberCommunity.getPassword());
        memberCommunity.setPassword(PW);
        //passwordEncoder.encode(memberCommunity.getPassword());


        memberCommunity.setActive(true);

        return ResponseEntity.ok(memberCommunityService.saveMemberCommunity(memberCommunity));
    }


    @Override
    public String login(Model model) {
        model.addAttribute("user",new Users());
        return "login";
    }


    @Override
    public String connexion(Users users, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(users.getLogin(), users.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Authenticated: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
            String role = authentication.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse("");
            System.out.println("Authenticated role: " + role);
            System.out.println("le role est ici ");

            if ("President".equals(role)) {
                return "redirect:";

            } else if ("MemberCommunity".equals(role)) {
                System.out.println("Hello");
                //c'est içi ou ca bloque fuck
                return "redirect:/office";

            } else {
                return "redirect:/default";
            }
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed");
            model.addAttribute("error", "Utilisateur ou mot de passe incorrect");
            return "login";
        }


    }


}
