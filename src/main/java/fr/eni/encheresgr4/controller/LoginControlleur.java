package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControlleur {

    private UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String loginGET() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println(bCryptPasswordEncoder.encode("azerty"));
        return "utilisateur/login";
    }

    @GetMapping("/creerCompte")
    public String newUserGET() {
        return "utilisateur/login";
    }

}
