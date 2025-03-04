package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.repository.ArticleVenduRepository;
import fr.eni.encheresgr4.repository.UtilisateurRepository;
import fr.eni.encheresgr4.repository.RetraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleVenduService implements ArticleVenduInterface {

    @Autowired
    ArticleVenduRepository articleRepository;

    @Autowired
    RetraitRepository retraitRepository;


    @Autowired
    UtilisateurRepository utilisateurRepository;

    public ArticleVendu ajouterArticleVendu(ArticleVendu articleVendu) {
        articleRepository.save(articleVendu);

        articleVendu.setNo_utilisateur(utilisateurRepository.findByPseudo(articleVendu.getNo_utilisateur().getPseudo()));

        if(articleVendu.getNo_article() == 0){
            retraitRepository.save(articleRepository.takeTheLastResult().getNo_article(), articleVendu);
            return articleRepository.takeTheLastResult();
        }
        else{
            retraitRepository.save(articleVendu.getNo_article(), articleVendu);
            return articleVendu;
        }
    }

    public void supprimerArticleVendu(int id) {
        articleRepository.delete(id);
    }

    @Override
    public ArticleVendu findOneById(int id) {
        return articleRepository.findOneById(id);
    }

    public List<ArticleVendu> getAllImageName() {
        return articleRepository.getAllImageName();
    }

    @Override
    public List<ArticleVendu> findAllArticleVendu() {
        return articleRepository.findAll();
    }

    @Override
    public List<ArticleVendu> listAllArticleVenduByName(String filterName, int filterCategory) {
        List<ArticleVendu> articleVendusByName = new ArrayList<>();
        List<ArticleVendu> data = articleRepository.findAll();
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
