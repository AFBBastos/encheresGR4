package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.repository.ArticleVenduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleVenduService {

    @Autowired
    ArticleVenduRepository repository;

    public void ajouterArticleVendu(ArticleVendu articleVendu) {
        repository.save(articleVendu);
    }

    public ArticleVendu findOneById(int id) {
        return repository.findOneById(id);
    }

    public List<ArticleVendu> findAllArticleVendu() {
        return repository.findAll();
    }

    public List<ArticleVendu> listAllArticleVenduByName(String filterName, int filterCategory) {
        List<ArticleVendu> articleVendusByName = new ArrayList<>();
        List<ArticleVendu> data = repository.findAll();
        for (ArticleVendu articleVendu : data) {
            String nomArticleVendu = articleVendu.getNom_article().toLowerCase();
            filterName = filterName.toLowerCase();
            if(nomArticleVendu.contains(filterName) ) {
                if(filterCategory == 0 || filterCategory == articleVendu.getNo_categorie().getNo_categorie()){
                    articleVendusByName.add(articleVendu);
                }
            }
        }
        return articleVendusByName;
    }
}
