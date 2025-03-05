package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.*;
import fr.eni.encheresgr4.service.ArticleVenduService;
import fr.eni.encheresgr4.service.CategorieService;
import fr.eni.encheresgr4.service.RetraitService;
import fr.eni.encheresgr4.service.EnchereService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/ventes")
public class ArticleVenduController {

    private final CategorieService categorieService;
    private final ArticleVenduService articlesVendusService;
    private final EnchereService enchereService;
    private final RetraitService retraitService;

    public ArticleVenduController(ArticleVenduService articlesVendusService, CategorieService categorieService, RetraitService retraitService, EnchereService enchereService) {
        this.articlesVendusService = articlesVendusService;
        this.categorieService = categorieService;
        this.retraitService = retraitService;
        this.enchereService = enchereService;
    }

    @GetMapping("/ajouter")
    public String ajouterGet(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();

        ArticleVendu article = new ArticleVendu();
        Retrait retrait = new Retrait(currentUser.getRue(), currentUser.getCode_postal(), currentUser.getVille(), article.getNo_article());
        article.setLieuRetrait(retrait);
        List<ArticleVendu> images = articlesVendusService.getAllImageName();
        List<Categorie> categories = categorieService.findAllCategorie();
        model.addAttribute("article",article);
        model.addAttribute("categories",categories);
        model.addAttribute("currentUser", currentUser);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("categories", categorieService.findAllCategorie());
        model.addAttribute("articlesVendus", articlesVendusService.listAllArticleVenduByName(filterByName, filterByCategorie));
        return "articleVendu/liste";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") final int id, Model model) {

        ArticleVendu articleVendu = articlesVendusService.findOneById(id);
        Enchere enchere = new Enchere();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUtilisateur = (Utilisateur) authentication.getPrincipal();

        Retrait retrait = retraitService.findOneRetraitById(id);
        articleVendu.setLieuRetrait(retrait);

        Enchere dernierEnchere = enchereService.findAllByArticle(id).stream().findFirst().orElse(null);

        model.addAttribute("articleVendu", articleVendu);
        model.addAttribute("enchere", enchere);
        model.addAttribute("currentUser", currentUtilisateur);
        model.addAttribute("dernierEnchere", dernierEnchere); // Peut être null

        return "articleVendu/detail";
    }

    @GetMapping("/modification/{id}")
    public String modification(@PathVariable("id") final int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUtilisateur = (Utilisateur) authentication.getPrincipal();

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
