package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.model.ArticleVendu;
import fr.eni.encheresgr4.model.Retrait;
import fr.eni.encheresgr4.repository.RetraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetraitService {

    @Autowired
    RetraitRepository repository;

    public void ajouterRetrait(ArticleVendu articleVendu) {
        repository.save(articleVendu.getNo_article(), articleVendu);
    }

    public Retrait findOneRetraitById(int id) { return repository.findOneById(id); }

    public List<Retrait> findAllRetrait() {
        return repository.findAll();
    }

}
