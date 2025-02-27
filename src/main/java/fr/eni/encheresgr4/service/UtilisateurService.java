package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {

    private List<Utilisateur> utilisateurs;

    public UtilisateurService() { utilisateurs = new ArrayList<>(); }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public List<Utilisateur> findAllUtilisateurs() { return utilisateurs; }

}
