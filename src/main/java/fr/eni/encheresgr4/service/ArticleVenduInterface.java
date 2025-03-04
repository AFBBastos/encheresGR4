package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.ArticleVendu;

import java.util.List;

public interface ArticleVenduInterface {

    public void ajouterArticleVendu(ArticleVendu articleVendu);

    public ArticleVendu findOneById(int id);

    public List<ArticleVendu> findAllArticleVendu();

    public List<ArticleVendu> listAllArticleVenduByName(String filterName, int filterCategory);


}
