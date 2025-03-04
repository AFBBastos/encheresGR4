package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.Objects;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/{id}")
    public String profil(@PathVariable("id") final int id, Model model) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();
        model.addAttribute("currentUser", currentUser);
        model.getAttribute("utilisateur");
        return "utilisateur/profil";
    }

    @GetMapping("/{id}/modificationProfil")
    public String modificationProfil(@PathVariable("id") final int id, Model model) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();
        model.addAttribute("currentUser", currentUser);
        return "utilisateur/modification";
    }

    @PostMapping("/modifier")
    public String modificationProfil(Utilisateur currentUser,
                                     @RequestParam("new-mot-de-passe") String newMotDePasse,
                                     @RequestParam("new-mot-de-passe-confirm") String newMotDePasseConfirm,
                                     RedirectAttributes redirectAttributes, Model model){

        if (!Objects.equals(newMotDePasse, newMotDePasseConfirm)) {
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("erreur", "Les mots de passes ne sont pas les mêmes.");
            return "utilisateur/modification";
        }

        if (!Objects.equals(newMotDePasse, "") && newMotDePasse.equals(newMotDePasseConfirm)) {
            utilisateurService.save(currentUser);
            redirectAttributes.addFlashAttribute("currentUser", currentUser);
            redirectAttributes.addFlashAttribute("id", currentUser.getNo_utilisateur());

            return "redirect:/utilisateur/" + currentUser.getNo_utilisateur();
        }

        utilisateurService.save(currentUser);

        redirectAttributes.addFlashAttribute("currentUser", currentUser);
        redirectAttributes.addFlashAttribute("id", currentUser.getNo_utilisateur());

        return "redirect:/utilisateur/" + currentUser.getNo_utilisateur();
    }

    @GetMapping("/{id}/modificationProfil/modifierMdp")
    public String modificationMdp(@PathVariable("id") final int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();
        model.addAttribute("currentUser", currentUser);
        return "utilisateur/modification";
    }
}
