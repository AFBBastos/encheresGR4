package fr.eni.encheresgr4.controller;


import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Retrait;
import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventes")
public class ArticleVenduController {

    @GetMapping("/ajouter")
    public String ajouterGet(Model model) {
        Utilisateur currentUtilisateur = new Utilisateur(1,"Dede","André", "Bastos","afb.bastos@gmail.com", "0123456789", "rue des machins", "44000", "Nantes", "azerty", 500, true);
        ArticleVendu article = new ArticleVendu();
        Retrait retrait = new Retrait(currentUtilisateur.getRue(), currentUtilisateur.getCodePostal(), currentUtilisateur.getVille(), article);
        article.setLieuRetrait(retrait);
        model.addAttribute("article",article);
        return "articleVendu/ajouter";
    }

    @PostMapping("/ajouter")
    public String ajouterPost(Model model, ArticleVendu article) {
        return "articleVendu/liste";
    }

}
