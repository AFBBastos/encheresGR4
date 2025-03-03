package fr.eni.encheresgr4.controller;

import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.Objects;

@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @GetMapping("/{id}")
    public String profil(@PathVariable("id") final int id, Model model) throws ParseException {
        Utilisateur currentUser = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        model.addAttribute("currentUser", currentUser);
        model.getAttribute("utilisateur");

        return "utilisateur/profil";
    }

    @GetMapping("/{id}/modificationProfil")
    public String modificationProfil(@PathVariable("id") final int id, Model model) throws ParseException {
        Utilisateur currentUser = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        model.addAttribute("currentUser", currentUser);
        return "utilisateur/modification";
    }

    @PostMapping("/modifier")
    public String modificationProfil(@RequestParam("pseudo") String pseudo,
                                     @RequestParam("name") String name,
                                     @RequestParam("last-name") String lastName,
                                     @RequestParam("email") String email,
                                     @RequestParam("telephone") String telephone,
                                     @RequestParam("rue") String rue,
                                     @RequestParam("code-postal") String codePostal,
                                     @RequestParam("ville") String ville,
                                     @RequestParam("mot-de-passe") String motDePasse,
                                     @RequestParam("new-mot-de-passe") String newMotDePasse,
                                     @RequestParam("new-mot-de-passe-confirm") String newMotDePasseConfirm,
                                     RedirectAttributes redirectAttributes, Model model){

        if (!Objects.equals(newMotDePasse, newMotDePasseConfirm)) {
            Utilisateur currentUser = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("erreur", "Les mots de passes ne sont pas les mêmes.");
            return "utilisateur/modification";
        }

        if (newMotDePasse != "" && Objects.equals(newMotDePasse, newMotDePasse)) {
            Utilisateur currentUser = new Utilisateur(1, pseudo, lastName, name, email, telephone, rue, codePostal, ville, newMotDePasse, 200, true);
            redirectAttributes.addFlashAttribute("currentUser", currentUser);
            redirectAttributes.addFlashAttribute("id", currentUser.getNo_utilisateur());

            return "redirect:/utilisateur/" + currentUser.getNo_utilisateur();
        }

        Utilisateur currentUser = new Utilisateur(1, pseudo, lastName, name, email, telephone, rue, codePostal, ville, motDePasse, 200, true);
        redirectAttributes.addFlashAttribute("currentUser", currentUser);
        redirectAttributes.addFlashAttribute("id", currentUser.getNo_utilisateur());

        return "redirect:/utilisateur/" + currentUser.getNo_utilisateur();
    }

    @GetMapping("/{id}/modificationProfil/modifierMdp")
    public String modificationMdp(@PathVariable("id") final int id, Model model) {
        Utilisateur currentUser = new Utilisateur(1, "Noix", "HRV", "Noä", "noa.hervieu2024@campus-eni.fr", "0123456789", "6 rue de la chose", "44100", "Nantes", "Mot2p@ssTè6qrizé", 200, true);
        model.addAttribute("currentUser", currentUser);
        return "utilisateur/modification";
    }

}
