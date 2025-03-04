package fr.eni.encheresgr4.service;

import fr.eni.encheresgr4.exceptions.EnchereException;
import fr.eni.encheresgr4.model.Enchere;
import fr.eni.encheresgr4.model.Utilisateur;
import fr.eni.encheresgr4.repository.CrudInterface;
import fr.eni.encheresgr4.repository.EnchereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EnchereService implements CrudInterface<Enchere> {

    @Autowired
    private EnchereRepository enchereRepository;

    @Override
    public Enchere findOneById(int id) {
        return null;
    }

    @Override
    public List<Enchere> findAll() {
        return List.of();
    }

    @Override
    public int save(Enchere enchere) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur currentUser = (Utilisateur) authentication.getPrincipal();

        List<Enchere> encheres = enchereRepository.findAllByArticle(enchere.getNo_article().getNo_article());
        Enchere enchereExistant = encheres.isEmpty() ? new Enchere() : encheres.get(0);

        if (enchere.getMontant_enchere() <= enchereExistant.getMontant_enchere() || enchere.getMontant_enchere() <= enchere.getNo_article().getPrix_initial()) {
            int montantMin = enchereExistant.getMontant_enchere() == 0 ? enchere.getNo_article().getPrix_initial() : enchereExistant.getMontant_enchere();
            throw new EnchereException("Vous devez mettre un montant supèrieur à " + montantMin);
        }
        if (enchere.getMontant_enchere() > currentUser.getCredit()) {
            throw new EnchereException("Vous n'avez pas assez de crédit");
        }
        if (Objects.equals(enchere.getNo_article().getNo_utilisateur().getPseudo(), currentUser.getPseudo())) {
            throw new EnchereException("Vous ne pouvez pas encherir sur votre propre vente");
        }

        enchereRepository.save(enchere);
        return enchere.getNo_article().getNo_article();
    }

    @Override
    public void delete(Enchere enchere) {

    }

    public List<Enchere> findAllByArticle(int id) {
        return enchereRepository.findAllByArticle(id);
    }
}
