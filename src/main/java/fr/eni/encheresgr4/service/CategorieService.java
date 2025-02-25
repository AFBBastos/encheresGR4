package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Categorie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService {
    private List<Categorie> categories;

    public CategorieService() {
        categories = new ArrayList<>();
    }

    public void ajouterCategorie(Categorie categorie) {
        categories.add(categorie);

    }

    public List<Categorie> findAllCategorie() {
        // TODO Auto-generated method stub
        return categories;
    }
}