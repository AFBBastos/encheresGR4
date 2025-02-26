package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.ArticleVendu;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleVenduService {

    private List<ArticleVendu> articlesVendus;

    public ArticleVenduService() {
        articlesVendus = new ArrayList<>();
    }

    public void ajouterArticleVendu(ArticleVendu articleVendu) {
        articlesVendus.add(articleVendu);

    }

    public List<ArticleVendu> findAllArticleVendu() {
        return articlesVendus;
    }

    public List<ArticleVendu> listAllArticleVenduByName(String filterName, int filterCategory) {
        List<ArticleVendu> articleVendusByName = new ArrayList<>();
        for (ArticleVendu articleVendu : articlesVendus){
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
