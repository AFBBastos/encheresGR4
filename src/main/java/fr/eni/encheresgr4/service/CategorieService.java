package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Categorie;
import fr.eni.encheresgr4.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository repository;

    public void ajouterCategorie(Categorie categorie) {
        repository.save(categorie);
    }

    public List<Categorie> findAllCategorie() {
        return repository.findAll();
    }
}