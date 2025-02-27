package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.*;
import fr.eni.encheresgr4.service.ArticleVenduService;
import fr.eni.encheresgr4.service.CategorieService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private ArticleVenduService articlesVendusService;

    public ArticleVenduController(ArticleVenduService articlesVendusService, CategorieService categorieService) {
        this.articlesVendusService = articlesVendusService;
        this.categorieService = categorieService;
    }

    @GetMapping("/ajouter")
    public String ajouterGet(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();

        ArticleVendu article = new ArticleVendu();
        Retrait retrait = new Retrait(currentUser.getRue(), currentUser.getCode_postal(), currentUser.getVille(), article.getNo_article());
        article.setLieuRetrait(retrait);
        List<Categorie> categories = categorieService.findAllCategorie();
        model.addAttribute("article",article);
        model.addAttribute("categories",categories);
        model.addAttribute("currentUtilisateur",currentUser);
        return "articleVendu/ajouter";
  }


    @PostMapping("/ajouter")
    public String ajouterPost(Model model, ArticleVendu article) {

        articlesVendusService.ajouterArticleVendu(article);

        return "redirect:/ventes";
    }

    @GetMapping
    public String listeArticleVendu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();

        model.addAttribute("articlesVendus", articlesVendusService.findAllArticleVendu());
        model.addAttribute("categories", categorieService.findAllCategorie());
        model.addAttribute("currentUser", currentUser);
        return "articleVendu/liste";
    }

    @PostMapping("/rechercher")
    public String filtrerArticleVendu(@RequestParam("filter-name") String filterByName, @RequestParam("categorie-select") int filterByCategorie, Model model) {
        model.addAttribute("categories", categorieService.findAllCategorie());
        model.addAttribute("articlesVendus", articlesVendusService.listAllArticleVenduByName(filterByName, filterByCategorie));
        return "articleVendu/liste";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") final int id, Model model) {

        ArticleVendu articleVendu = articlesVendusService.findOneById(id);
        Enchere enchere = new Enchere();
        Enchere dernierEnchere = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUtilisateur = (Utilisateur) authentication.getPrincipal();

        // TODO A MODIF SUR S003 et S004
//        Enchere dernierEnchere = null;
//        dernierEnchere = new Enchere(new Date(), 100, currentUser, articleVendu);

        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("enchere", enchere);
        model.addAttribute("currentUser", currentUtilisateur);
        model.addAttribute("dernierEnchere", dernierEnchere); // Peut être null

        return "articleVendu/detail";
    }
}
