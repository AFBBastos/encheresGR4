package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.*;
import fr.eni.encheresgr4.service.ArticleVenduService;
import fr.eni.encheresgr4.service.CategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ventes")
public class ArticleVenduController {

    private final CategorieService categorieService;
    private ArticleVenduService articlesVendusService;

    public ArticleVenduController(ArticleVenduService articlesVendusService, CategorieService categorieService) {
        this.articlesVendusService = articlesVendusService;
        this.categorieService = categorieService;
    }

    @GetMapping("/ajouter")
    public String ajouterGet(Model model) {
        Utilisateur currentUtilisateur = new Utilisateur(1,"Dede","André", "Bastos","afb.bastos@gmail.com", "0123456789", "rue des machins", "44000", "Nantes", "azerty", 500, true);
        ArticleVendu article = new ArticleVendu();
        Retrait retrait = new Retrait(currentUtilisateur.getRue(), currentUtilisateur.getCode_postal(), currentUtilisateur.getVille(), article.getNo_article());
        article.setLieuRetrait(retrait);
        model.addAttribute("article",article);
        return "articleVendu/ajouter";
  }


    @PostMapping("/ajouter")
    public String ajouterPost(Model model, ArticleVendu article) {
        return "articleVendu/liste";
    }

    @GetMapping()
    public String listeArticleVendu(Model model) {

        Utilisateur utilisateur = new Utilisateur(1, "CLmrch", "Lmrch", "Cloé", "lmrch@gmail.com", "0123456789", "Jacques prévert", "44220", "Coueron", "azerty1234", 200, false);
        Categorie informatique = new Categorie(1, "Informatique");
        Categorie vetement = new Categorie(2, "Vêtement");
        ArticleVendu articleVendu1 = new ArticleVendu(1,"PS2","Playstation 2, en bon état", new Date(), new Date(), 100, 100, "En cours", informatique, utilisateur);
        ArticleVendu articleVendu2 = new ArticleVendu(2,"T-shirt Kiabi","T-shirt Kiabi blanc, taille M", new Date(), new Date(), 10, 10, "En cours", vetement, utilisateur);

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

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") final int id, Model model) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Enchere enchere = new Enchere();

        Utilisateur currentUser = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        Utilisateur utilisateur = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        Categorie categorie = new Categorie(1, "Gaming");
        ArticleVendu articleVendu = new ArticleVendu(1, "PS2", "PlayStation 2", dateFormat.parse("2025-02-20 14:00:00"), dateFormat.parse("2025-02-23 14:00:00"), 50, 50, "En cours", categorie, utilisateur);
        Retrait retrait = new Retrait("5 rue du truc", "4400", "Nantes", articleVendu.getNo_article());
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
