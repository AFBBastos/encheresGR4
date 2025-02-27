package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") final int id, Model model) throws ParseException {
        Utilisateur currentUser = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        model.addAttribute("currentUser", currentUser);

        return "utilisateur/profil";
    }

}
