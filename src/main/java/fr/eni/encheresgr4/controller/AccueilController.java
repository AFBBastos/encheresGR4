package fr.eni.encheresgr4.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AccueilController {

    @GetMapping("/")
    public String accueil() {
        return "redirect:/ventes";
    }

}
