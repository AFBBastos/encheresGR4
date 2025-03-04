package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.repository.ArticleVenduRepository;
import fr.eni.encheresgr4.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleVenduService implements ArticleVenduInterface {

    @Autowired
    ArticleVenduRepository repository;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public void ajouterArticleVendu(ArticleVendu articleVendu) {
        articleVendu.setNo_utilisateur(utilisateurRepository.findByPseudo(articleVendu.getNo_utilisateur().getPseudo()));
        repository.save(articleVendu);
    }

    @Override
    public ArticleVendu findOneById(int id) {
        return repository.findOneById(id);
    }

    @Override
    public List<ArticleVendu> findAllArticleVendu() {
        return repository.findAll();
    }

    @Override
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
