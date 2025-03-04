package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.*;
import fr.eni.encheresgr4.service.ArticleVenduService;
import fr.eni.encheresgr4.service.CategorieService;
import fr.eni.encheresgr4.service.RetraitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ventes")
public class ArticleVenduController {

    private final CategorieService categorieService;
    private final ArticleVenduService articlesVendusService;
    private final RetraitService retraitService;

    public ArticleVenduController(ArticleVenduService articlesVendusService, CategorieService categorieService, RetraitService retraitService) {
        this.articlesVendusService = articlesVendusService;
        this.categorieService = categorieService;
        this.retraitService = retraitService;
    }

    @GetMapping
    public String listeArticleVendu(Model model) {
        model.addAttribute("articlesVendus", articlesVendusService.findAllArticleVendu());
        model.addAttribute("categories", categorieService.findAllCategorie());
        return "articleVendu/liste";
    }

    @PostMapping("/rechercher")
    public String filtrerArticleVendu(@RequestParam("filter-name") String filterByName, @RequestParam("categorie-select") int filterByCategorie, Model model) {
        model.addAttribute("categories", categorieService.findAllCategorie());
        model.addAttribute("articlesVendus", articlesVendusService.listAllArticleVenduByName(filterByName, filterByCategorie));
        return "articleVendu/liste";
    }

    @GetMapping("/ajouter")
    public String ajouterGet(Model model) {
        Utilisateur currentUtilisateur = new Utilisateur(1,"Dede","André", "Bastos","afb.bastos@gmail.com", "0123456789", "rue des machins", "44000", "Nantes", "azerty", 500, true);
        ArticleVendu article = new ArticleVendu();
        Retrait retrait = new Retrait(currentUtilisateur.getRue(), currentUtilisateur.getCode_postal(), currentUtilisateur.getVille(), article.getNo_article());
        article.setLieuRetrait(retrait);
        List<ArticleVendu> images = articlesVendusService.getAllImageName();
        List<Categorie> categories = categorieService.findAllCategorie();
        model.addAttribute("article",article);
        model.addAttribute("categories",categories);
        model.addAttribute("currentUtilisateur",currentUtilisateur);
        model.addAttribute("modificationOuAjout", "ajout");
        model.addAttribute("images", images);
        return "articleVendu/ajouter";
  }


    @PostMapping("/ajouter")
    public String ajouterPost(@RequestParam("rue") String articleRue,
                              @RequestParam("code_postal") String articleCodePostal,
                              @RequestParam("ville") String articleVille,
                              Model model, ArticleVendu article) {

        Retrait retrait = new Retrait(articleRue, articleCodePostal, articleVille);
        article.setLieuRetrait(retrait);

        int lastArticle = articlesVendusService.ajouterArticleVendu(article).getNo_article();

        return "redirect:/ventes/" + String.valueOf(lastArticle);
    }


    @GetMapping("/{id}")
    public String detail(@PathVariable("id") final int id, Model model) {

        ArticleVendu articleVendu = articlesVendusService.findOneById(id);
        Enchere enchere = new Enchere();

        Utilisateur currentUser = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        Retrait retrait = retraitService.findOneRetraitById(id);
        articleVendu.setLieuRetrait(retrait);

        // TODO A MODIF SUR S003 et S004
        Enchere dernierEnchere = null;
        dernierEnchere = new Enchere(new Date(), 100, currentUser, articleVendu);

        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("enchere", enchere);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("dernierEnchere", dernierEnchere); // Peut être null

        return "articleVendu/detail";
    }

    @GetMapping("/modification/{id}")
    public String modification(@PathVariable("id") final int id, Model model) {
        Utilisateur currentUtilisateur = new Utilisateur(1,"Dede","André", "Bastos","afb.bastos@gmail.com", "0123456789", "rue des machins", "44000", "Nantes", "azerty", 500, true);
        ArticleVendu articleVendu = articlesVendusService.findOneById(id);
        Retrait retrait = retraitService.findOneRetraitById(id);
        articleVendu.setLieuRetrait(retrait);
        List<Categorie> categories = categorieService.findAllCategorie();
        model.addAttribute("article",articleVendu);
        model.addAttribute("categories",categories);
        model.addAttribute("currentUtilisateur",currentUtilisateur);
        model.addAttribute("modificationOuAjout", "modification");

        return "articleVendu/ajouter";
    }


    @GetMapping("/delete/{id}")
    public String suppressionArticle(@PathVariable("id") final int id){
        articlesVendusService.supprimerArticleVendu(id);
        return "redirect:/ventes";
    }

}
