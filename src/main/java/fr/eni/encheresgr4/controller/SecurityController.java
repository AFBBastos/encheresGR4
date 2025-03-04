package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {


    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String loginGET() {
        return "utilisateur/login";
    }

    @GetMapping("/inscription")
    public String inscriptionGET(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateur/inscription";
    }

    @PostMapping("/inscription")
    public String inscriptionPOST(Utilisateur utilisateur) {

        Utilisateur user = new Utilisateur();

        user.setPseudo(utilisateur.getPseudo());
        user.setNom(utilisateur.getNom());
        user.setPrenom(utilisateur.getPrenom());
        user.setEmail(utilisateur.getEmail());
        user.setTelephone(utilisateur.getTelephone());
        user.setRue(utilisateur.getRue());
        user.setCode_postal(utilisateur.getCode_postal());
        user.setVille(utilisateur.getVille());
        user.setMot_de_passe(utilisateur.getMot_de_passe());

        utilisateurService.saveWithPassword(user);

        return "redirect:/login";
    }

}
