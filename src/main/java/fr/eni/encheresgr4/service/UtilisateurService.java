package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void save(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    public void saveWithPassword(Utilisateur utilisateur) {
        utilisateurRepository.saveWithPassword(utilisateur);
    }

    public List<Utilisateur> findAllUtilisateur() { return utilisateurRepository.findAll(); }

}
