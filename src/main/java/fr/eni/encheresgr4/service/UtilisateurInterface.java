package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.Utilisateur;

import java.util.List;

public interface UtilisateurInterface {

    public void save(Utilisateur utilisateur);

    public void saveWithPassword(Utilisateur utilisateur);

    public List<Utilisateur> findAllUtilisateur();

}
