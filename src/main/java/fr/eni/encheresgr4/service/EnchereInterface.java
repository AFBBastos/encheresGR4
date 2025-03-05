package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.Enchere;

import java.util.List;

public interface EnchereInterface {

    public List<Enchere> findAllByArticle(int id);

    public int save(Enchere enchere);

}
