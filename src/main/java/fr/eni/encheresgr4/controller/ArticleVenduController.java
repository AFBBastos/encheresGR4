package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/ventes")
public class ArticleVenduController {

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") final int id, Model model) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Enchere enchere = new Enchere();

        Utilisateur currentUser = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        Utilisateur utilisateur = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        Categorie categorie = new Categorie(1, "Gaming");
        ArticleVendu articleVendu = new ArticleVendu(1, "PS2", "PlayStation 2", dateFormat.parse("2025-02-20 14:00:00"), dateFormat.parse("2025-02-23 14:00:00"), 50, 50, "En cours", categorie, utilisateur);
        Retrait retrait = new Retrait("5 rue du truc", "4400", "Nantes", articleVendu);
        articleVendu.setLieuRetrait(retrait);

        Enchere dernierEnchere = null;
        dernierEnchere = new Enchere(new Date(), 100, currentUser, articleVendu);

        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("enchere", enchere);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("dernierEnchere", dernierEnchere); // Peut être null

        return "articleVendu/detail";
    }
}
