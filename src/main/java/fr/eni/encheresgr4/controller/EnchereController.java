package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.exceptions.EnchereException;
import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Enchere;
import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.service.ArticleVenduService;
import fr.eni.encheresgr4.service.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/encheres")
public class EnchereController {

    @Autowired
    private ArticleVenduService articleVenduService;

    @Autowired
    private EnchereService enchereService;

    @PostMapping("/{idVente}")
    public String ajouterEnchere(@PathVariable("idVente") final int idVente, @RequestParam("montant_enchere") int montant_enchere, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();

        ArticleVendu articleVendu = articleVenduService.findOneById(idVente);

        Enchere enchere = new Enchere();
        enchere.setNo_utilisateur(currentUser);
        enchere.setNo_article(articleVendu);
        enchere.setNo_utilisateur(currentUser);
        enchere.setMontant_enchere(montant_enchere);
        Enchere dernierEnchere = enchereService.findAllByArticle(idVente).stream().findFirst().orElse(null);

        try {
            enchereService.save(enchere);
        }catch (EnchereException e){
            System.out.println("ERREUR TRY CATCH !!!!");
            model.addAttribute("errorEnchereMontant", e.getMessage());
            model.addAttribute("articleVendu", articleVendu);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("enchere", enchere);
            model.addAttribute("dernierEnchere", dernierEnchere);
            return "articleVendu/detail";
        }

        return "redirect:/ventes/" + enchere.getNo_article().getNo_article();
    }

}
