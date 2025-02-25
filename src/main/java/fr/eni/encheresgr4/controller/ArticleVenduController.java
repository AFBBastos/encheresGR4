package fr.eni.encheresgr4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventes")
public class ArticleVenduController {

    @GetMapping
    public String listeArticleVendu() {
        System.out.println("dans listeArticleVendu");
       return "listeArticleVendu";
    }

}