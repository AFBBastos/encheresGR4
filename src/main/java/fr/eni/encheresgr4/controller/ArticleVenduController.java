package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Categorie;
import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.service.ArticleVenduService;
import fr.eni.encheresgr4.service.CategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Controller
@RequestMapping("/ventes")
public class ArticleVenduController {

    private final CategorieService categorieService;
    private ArticleVenduService articlesVendusService;

    public ArticleVenduController(ArticleVenduService articlesVendusService, CategorieService categorieService) {
        this.articlesVendusService = articlesVendusService;
        this.categorieService = categorieService;
    }

    @GetMapping()
    public String listeArticleVendu(Model model) {

        Utilisateur utilisateur = new Utilisateur(1, "CLmrch", "Lmrch", "Cloé", "lmrch@gmail.com", "0123456789", "Jacques prévert", "44220", "Coueron", "azerty1234", 200, false);
        Categorie informatique = new Categorie(1, "Informatique");
        Categorie vetement = new Categorie(2, "Vêtement");
        ArticleVendu articleVendu1 = new ArticleVendu(1,"PS2","Playstation 2, en bon état", new Date(), new Date(), 100, 100, "En cours", informatique, utilisateur);
        ArticleVendu articleVendu2 = new ArticleVendu(2,"T-shirt Kiabi","T-shirt Kiabi blanc, taille M", new Date(), new Date(), 10, 10, "En cours", vetement, utilisateur);
        System.out.println("dans listeArticleVendu");

        articlesVendusService.ajouterArticleVendu(articleVendu1);
        articlesVendusService.ajouterArticleVendu(articleVendu2);
        categorieService.ajouterCategorie(informatique);
        categorieService.ajouterCategorie(vetement);

        model.addAttribute("articlesVendus", articlesVendusService.findAllArticleVendu());
        model.addAttribute("categories", categorieService.findAllCategorie());
        return "articleVendu/liste";
    }

    @PostMapping("/rechercher")
    public String filtrerArticleVendu(@RequestParam("filter-name") String filterByName, @RequestParam("categorie-select") int filterByCategorie, Model model) {
        Categorie informatique = new Categorie(1, "Informatique");
        Categorie vetement = new Categorie(2, "Vêtement");
        categorieService.ajouterCategorie(informatique);
        categorieService.ajouterCategorie(vetement);

        model.addAttribute("categories", categorieService.findAllCategorie());
        model.addAttribute("articlesVendus", articlesVendusService.listAllArticleVenduByName(filterByName, filterByCategorie));
        return "articleVendu/liste";
    }
}